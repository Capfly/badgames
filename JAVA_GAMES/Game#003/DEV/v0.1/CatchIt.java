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

 	CatchIt(int width,int height) {

        super("CATCH IT NOW!");
		setSize(width,height);
		setBackground(Color.black);
		setLayout(new FlowLayout());

		this.addKeyListener(this); // WTF?!

		setVisible(true);
	}

	Point player = new Point("Spieler",Color.black);

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
    }

		public void paint(Graphics g) {

        	        g.setColor(Color.white);
        	        g.fillRect(this.breakX+this.gpstart,this.breakY+this.gpstart,this.MAXW,this.MAXH);

			g.setColor(player.getColor());
			g.fillRect(player.getX()+this.breakX,player.getY()+this.breakY,this.STDW,this.STDH);
			//g.fillRect();
	}

	public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
 }
