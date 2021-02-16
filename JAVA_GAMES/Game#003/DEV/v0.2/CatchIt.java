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
	}

	public int cRandN(int MAX,int wNot) {

	    int v;

        do { v = (int)(Math.random()*100)*(MAXW/100); }
        while(v % 10 != 0 && v != wNot);

        return v;
	}

	Point player = new Point("Spieler",Color.red,190,190);
	Point target = new Point("Ziel",Color.green,cRandN(STDW,player.getX()),cRandN(STDH,player.getY()));

	public void run() {

/*		while(true) {

			try { Thread.sleep(20); }
			catch(InterruptedException ie) {}
		}*/
	}

	public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

            default: System.out.println("Fehler #"+e.getKeyCode()+"# Die Taste ist nicht vergeben!"); break;
            case 27: System.exit(0); break;
            case 38: player.addToY(-STDH); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
            case 39: player.addToX(STDW); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
            case 40: player.addToY(STDH); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
            case 37: player.addToX(-STDW); repaint(this.gpstart+this.breakX,this.gpstart+this.breakY,this.MAXW,this.MAXH); break;
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
                g.drawString("Bitte ESC-Taste drücken, um das Spiel zu beenden.",25,80);
            }
	}

	public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
 }
