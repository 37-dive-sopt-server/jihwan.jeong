package org.sopt.repository;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileMemberRepository extends Thread {

    private static final File file = new File("members.csv");
    private static final BlockingQueue<Member> addQueue = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Long> removeQueue = new LinkedBlockingQueue<>();
    private boolean running = true;

    public FileMemberRepository() {
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("파일 생성 실패: " + e.getMessage());
        }
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        while (running) {
            try{
                Member member = addQueue.take();
                saveToFile(member);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    @Override
    public void interrupt() {
        running = false;
        removeFromQueue();
        super.interrupt();
    }

    public void save(Member member) {
        addQueue.offer(member);
    }

    private synchronized void saveToFile(Member member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(
                    String.format("%s,%s,%s,%s,%s\n",
                        member.getId().toString(),
                        member.getName(),
                        member.getEmail(),
                        member.getGender(),
                        member.getBirthdate()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException("파일에 데이터 저장 실패 " + e.getMessage());
        }
    }

    public void remove(Long id) {
        removeQueue.offer(id);
    }

    private synchronized void removeFromQueue() {
        List<String> updatedLines = new ArrayList<>();
        List<Long> removeIds = new ArrayList<>();

        while (!removeQueue.isEmpty()) {
            removeIds.add(removeQueue.poll());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!removeIds.contains(Long.parseLong(parts[0]))) {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 쓰기 실패: " + e.getMessage());
        }
    }

    public List<Member> load() {
        List<Member> members = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                members.add(new Member(
                        Long.parseLong(parts[0]),
                        parts[1],
                        parts[2],
                        Gender.fromString(parts[3]),
                        parts[4]
                ));
            }
            return members;
        } catch (IOException e) {
            throw new RuntimeException("멤버 데이터 파일 로드 실패 " + e.getMessage());
        }
    }
}
