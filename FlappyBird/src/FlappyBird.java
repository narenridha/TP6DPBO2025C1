import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    // Background
    int frameWidth = 360;
    int frameHeight = 640;
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    int playerStartPosX = frameWidth/8;
    int playerStartPosY = frameHeight/2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    // pipes attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // game logic
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    boolean isGameStarted = false;
    boolean isGameOver = false;
    int score = 0;

    //GUI
    JButton restartButton;

    // constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("/assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("/assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("/assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(3500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        // restart button
        restartButton = new JButton("Restart");
        restartButton.setBounds(110, 300, 120, 40); // posisi dan ukuran tombol
        restartButton.setFocusable(false);
        restartButton.setVisible(false); // awalnya tidak terlihat

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        setLayout(null); // agar tombol bisa diposisikan manual
        add(restartButton);

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(),pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // Tampilan untuk awal game
        if (!isGameStarted) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            String msg = "Tekan SPASI untuk mulai";
            FontMetrics metrics = g.getFontMetrics();
            int x = (frameWidth - metrics.stringWidth(msg)) / 2;
            int y = frameHeight / 2;
            g.drawString(msg, x, y);
        }

        // Tampilan GameOver
        if (isGameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            String msg = "Tekan (R) atau klik tombol untuk restart.";
            FontMetrics metrics = g.getFontMetrics();
            int x = (frameWidth - metrics.stringWidth(msg)) / 2;
            int y = frameHeight / 2 - 100;
            g.drawString(msg, x, y);

            restartButton.setVisible(true); // tampilkan tombol saat game over
        } else {
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString(String.valueOf(score), frameWidth / 2 - 10, frameHeight / 2 - 200);

            restartButton.setVisible(false); // sembunyikan kalau tidak game over
        }

    }

    public void move(){
        if (!isGameStarted || isGameOver) return;

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }

        // Game over jika burung jatuh ke bawah layar
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver();
        }

        // Deteksi tabrakan dengan pipa
        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

            if (pipeRect.intersects(playerRect)) {
                gameOver();
            }
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // Cek jika pipe belum dilewati dan posisi burung sudah lewat
            if (!pipe.passed && pipe.getImage() == upperPipeImage &&
                    pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                score++;
                System.out.println("Score: " + score);
            }
        }
    }

    public void placePipes(){
        int randomPosY = (int)(pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void gameOver() {
        gameLoop.stop();
        pipesCooldown.stop();
        isGameOver = true;
        repaint();
        //System.out.println("Game Over! Tekan R untuk restart.");
    }

    public void restartGame() {
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        pipes.clear();

        isGameOver = false;
        isGameStarted = false;
        score = 0;
        gameLoop.start();
        pipesCooldown.start();

        restartButton.setVisible(false);
        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!isGameStarted) {
                isGameStarted = true;
                pipes.clear(); // Hapus semua pipa lama
                pipesCooldown.start(); // Mulai generate pipa
            }
            if (!isGameOver) {
                player.setVelocityY(-10); // burung lompat
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_R && isGameOver) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }
}
