import java.io.*;
import java.awt.*;
import java.awt.event.*;

 class CatchIt extends Frame implements KeyListener,Runnable {

    public static final int STDW = 10;
    public static final int STDH = 10;
    public static final int MAXW = 400;
    public static final int MAXH = 400;
    public static final int breakX = 8;
    public static final int breakY = 30;
    public static final int gpstart = 20;
    public static boolean gEnd = false;

 	CatchIt(int width,int height) {

        super("CATCH IT NOW!");
		setSize(width,height);
		setBackground(Color.black);
		setLayout(new FlowLayout());

		this.addKeyListener(this); // WTF?!

		setVisible(true);

                Jump jump = new Jump(target);
                new Thread(jump).start();
	}

	static int cRandN(int MAX,int wNot,int mg) {

	    int v;

        do { v = (int)(Math.random()*100)*(MAXW/100); }
        while(v % 10 != 0 && v != wNot || v < mg);

        return v;
	}

	Point player = new Point("Spieler",Color.red,190,190);
	Point target = new Point("Ziel",Color.blue,cRandN(STDW,player.getX(),(gpstart+breakX)),cRandN(STDH,player.getY(),(gpstart+breakY)));

	public void run() {

                while(true) {

                    if(!gEnd) {
                            try { Thread.sleep(100); }
                            catch(InterruptedException ie) {}
                            repaint();
                    }
                }
	}

	public static void Jump(Point obj) {

        obj.setX(cRandN(STDW,obj.getX(),gpstart+breakX));
        obj.setY(cRandN(STDH,obj.getY(),gpstart+breakY));
	}

	public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

            default: System.out.println("Fehler #"+e.getKeyCode()+"# Die Taste ist nicht vergeben!"); break;
            case 27: System.exit(0); break;
            case 38: player.addToY(-STDH); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
            case 39: player.addToX(STDW); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
            case 40: player.addToY(STDH); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
            case 37: player.addToX(-STDW); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
            case 113: player.setX(190); player.setY(190); gEnd = false; repaint(); break;
            case 69: if(e.isControlDown()) { Jump(target); } break;
        }

        if(player.checkCatch(target) == true) { gEnd = true; repaint(); }
    }

		public void paint(Graphics g) {

            if(gEnd == false) {

                g.setColor(Color.white);
                g.fillRect(this.breakX+this.gpstart,this.breakY+this.gpstart,this.MAXW,this.MAXH);

                g.setColor(target.getColor());
                g.fillRect(target.getX()+this.breakX,target.getY()+this.breakY,this.STDW,this.STDH);

                g.setColor(player.getColor());
                g.fillRect(player.getX()+this.breakX,player.getY()+this.breakY,this.STDW,this.STDH);
            }
            else {

                g.setColor(Color.white);
                g.setFont(new Font("default",Font.BOLD,30));
                g.drawString("Spiel gewonnen.",15,60);
                g.setFont(new Font("default",Font.BOLD,10));
                g.drawString("ESC-Taste: Spiel beenden.",25,80);
		g.drawString("F2-Taste: Spiel neu starten.",25,92);
            }
	}

	public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
 }
