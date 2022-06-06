	/**
  * Description: 
  * Create a drawing program for a specific scene and demonstrate various input capabilities.
  * @author: Tao A
  */

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;


public class Sketch extends PApplet {

  PImage fg;
	PImage bg;
  static final int WIDTH = 536;
  static final int HEIGHT = 321;
  static final int ACCURACY = 50;
  int x = 0;
  int y = 5;
  boolean alive = true;
  int score = 0;
  int minionNumber = 1;
  int bgNumber = 1;
  
  /**
   * This method sets up the size of drawing
   * @param
   * @return
   */
  public void settings() {
	// put your size call here
    size(WIDTH, HEIGHT);
  }

  /** 
   * This method sets up the background
   * @param
   * @return
   */
  public void setup() {
    bg = loadImage("background/bg" + bgNumber + ".jpg");
  }

  /**
   * This method draws the chosen minion character on screen
   * @param
   * @return
   */
  public void draw() {
  
    background(bg);

    if (alive) {
      fg = loadImage("image/minion" + minionNumber + ".png");
      x = (x + 5) % WIDTH;
      y = (y + 5) % HEIGHT;
    } else {
      fg = loadImage("image/explode.png");      
    }
    
    image(fg, x, y, 100, 100);
    drawCrossHair();
    text("Score: " + score, WIDTH - 100, 20);
  }

  /**
   * This method draws a cross-hair
   * @param
   * @return
   */
  public void drawCrossHair() {
    fill(255, 2, 2);
    rect(mouseX -25, mouseY, 50, 3);
    rect(mouseX, mouseY - 25, 3, 50);
    ellipse(mouseX, mouseY, 10, 10);   
  }
  
  // define other methods down here.
  /**
   * The method mouseClick shoots the minion. If the shot is accurate, the minion is dead. Otherwise, the minion remains alive.
   * @param
   * @return
   */
  public void mouseClicked() {
    if (Math.abs(x + 50 - mouseX) < ACCURACY &&
        Math.abs(y + 50 - mouseY) < ACCURACY) {
      alive = false;  
      ++score;
    }
  }

  /**
   * This method implements all the user keyboard implements
   * key r resets score
   * key 1 2 3 4 changes minion character
   * space key revives minion
   * key b changes background
   * mouse click shoots the minion
   * @param
   * @return
   */
  public void keyPressed() {
    if (key == ' ') {
      alive = true;
    } else if (key == 'r') {
      score = 0;
    } else if (key >= '1' && key <= '4') {
      minionNumber = key - '1' + 1;
    } else if (key == 'b') {
      if (bgNumber == 1) {
        bgNumber = 2;
      } else {
        bgNumber = 1;
      }
      bg = loadImage("background/bg" + bgNumber + ".jpg");
    }
  }
}