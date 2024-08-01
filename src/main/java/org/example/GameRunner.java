package org.example;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
        // Play Background Music
        String filepath = "resources/CasinoJazz.wav";
        PlayMusic music = new PlayMusic();
        music.playMusic(filepath);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String playerName = sc.nextLine();
        System.out.println("Enter your starting balance: ");
        int playerBalance = sc.nextInt();

        Game game = new Game(playerName, playerBalance);
        game.play();
    }
}


