import java.io.*;
import java.awt.*;
import java.awt.event.*;

 class CatchIt extends Frame implements KeyListener,Runnable {

    public static final int STDW = 10;
    public static final int STDH = 10;
    public static final int MAXW = 700;
    public static final int MAXH = 400;
    public static final int breakX = 8;
    public static final int breakY = 30;
    public static final int gpstart = 20;
    private static boolean toRight = true;
    private static boolean toLeft = false;
    private static boolean toDown = false;
    private static int setMark = 0;

    private static int markPos = MAXH;
    private static int points = 0; // PUNKTE
    private static int ebene = 1;
    private static int level = 1;
    private static boolean relAll = false;

    protected static boolean dev = false; // development mode

    private static int status = 0;
    /* 0 = Game on
       1 = Game paused
       2 = Game end
    */

 	CatchIt(int width,int height) {

        super("CATCH IT!");
		setSize(width,height);
		setBackground(Color.black);
		setLayout(new FlowLayout());

		this.addKeyListener(this); // WTF?!

		setVisible(true);
	}

	Point player = new Point("Spieler",Color.black);
	Point mark = new Point("Marker",Color.yellow,-20,-20);
	Point target = new Point("Ziel",Color.red,randIt(STDW,gpstart,MAXW),MAXH-STDH);

	public void run() {

      while(true) {

        if(status == 0) {
            if(toRight) {
                if(player.getX() <= MAXW) player.addToX(STDW);
                else {
                    toRight = false; toLeft = true;
                    addPoints(-1);
                    System.out.println("Change#toLeft=true");
                }
            }

            if(toLeft) {
                if(player.getX() > gpstart) player.addToX(-STDW);
                else {
                    toLeft = false; toRight = true;
                    addPoints(-1);
                    System.out.println("Change#toRight=true");
                }
            }

            if(toDown) {
                if(toLeft == true || toRight == true) { System.out.println("ERROR!"); System.exit(0); }
                if(player.getY() <= MAXH) player.addToY(STDH);
                else {
                    toDown = false;
                    System.out.println("Phase beendet.");

                    if(target.getX() == mark.getX() && target.getY() == mark.getY()) {
                        System.out.println("Marker passt, back.");
                        addPoints((40*level-ebene));

                        ebene = 1;
                        target.setY(MAXH+STDH);
                        target.setX(randIt(STDW,gpstart,MAXW));
                        level = points/500+1;
                        if(level > 10) level = 10;
                    }
                    else {

                        if(target.getY() > this.gpstart+STDH) {
                            System.out.println("Marker verpasst, -STDH nun.");
                            addPoints(-2*level);
                            ebene++;
                            target.addToY(-STDH);
                            target.setX(randIt(STDW,gpstart,MAXW));
                        }
                        else {
                            System.out.println("################## Spiel beendet ##################");
                            status = 2;
                        }
                    }
                    player.setX(this.gpstart);
                    player.setY(this.gpstart);
                    setMark = 0;
                    toLeft = false;
                    toDown = false;
                    toRight = true;

                    /*try {
                        if(relAll) { repaint(); relAll = false; }
                        else repaint(this.breakX+this.gpstart,this.breakY+this.gpstart,this.MAXW,this.MAXH);
                        Thread.sleep(300);
                    }
                    catch (InterruptedException e) {}*/
                }
            }
        }

        try {
	    if(status != 2) Thread.sleep(40-level*3);
	    else Thread.sleep(300);

            if(relAll) { repaint(); relAll = false; }
            else repaint(this.breakX+this.gpstart,this.breakY+this.gpstart,this.MAXW,this.MAXH);
        }
        catch (InterruptedException e) {}
      }
    }

    private void addPoints(int points) {

        this.points += points;
        relAll = true;
    }

    public int randIt(int std,int min,int max) {

        int rWert = 1;

        do {
            rWert = (int)(Math.random()*max);
        }
        while(rWert%std != 0 || rWert > (max-std) || (rWert < min));

        return rWert;
    }

	public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

            default: System.out.println("Fehler #"+e.getKeyCode()+"# Die Taste ist nicht vergeben!"); break;
            case 27: System.exit(0); break;
            case 80:
                switch(status) {
                    case 0: if(status != 2) status = 1; break;
                    case 1: if(status != 2) status = 0; break;
                }
            break;
            case 32:
                if(status == 0) {
                    if(setMark == 0) {
                        toLeft = false;
                        toRight = false;
                        toDown = true;
                        setMark = 1;
                        System.out.println("Change#down=true,setMark=1");
                    }
                    else {
                        mark.setX(player.getX());
                        mark.setY(player.getY());
                        setMark = 2;
                    }
                }
            break;
	    case 113:
		// Gehe zu keyReleased
	    break;
	    case 114:
		if(dev == true) this.status = 2; // Dev-Mode only, ends game.
	    break;
        }
    }

	public void update(Graphics g) {

		paint(g);
	}
	public void paint(Graphics g) {

        if(status != 2) {
            g.setColor(Color.white);
            g.fillRect(this.breakX+this.gpstart,this.breakY+this.gpstart,this.MAXW,this.MAXH);

            g.setColor(Color.black);
            g.fillRect(this.breakX+gpstart,this.breakY,this.MAXW,this.gpstart);
            if(points <= 0) { points = 0; g.setColor(Color.red); }
            else g.setColor(Color.green);
            g.setFont(new Font("Lucida Console",Font.BOLD,15));
	    if(points == 404) { g.drawString("Punkte: "+points+" - Points not found :-), Ebene "+ebene+", Level "+level,10,44); }
	    else { g.drawString("Punkte: "+points+", Ebene "+ebene+", Level "+level,10,44); }

            g.setColor(target.getColor());
            g.fillRect(target.getX()+this.breakX,target.getY()+this.breakY,this.STDW,this.STDH);


            if(setMark == 2) {
                g.setColor(mark.getColor());
                g.fillRect(mark.getX()+this.breakX,mark.getY()+this.breakY,this.STDW,this.STDH);
            }

            g.setColor(player.getColor());
            g.fillRect(player.getX()+this.breakX,player.getY()+this.breakY,this.STDW,this.STDH);

            if(status == 1) {

                g.setColor(Color.white);
                g.fillRect(this.breakX+this.gpstart,this.breakY+this.gpstart,this.MAXW,this.MAXH);

                g.setColor(Color.red);
                g.drawString("Spiel unterbrochen, drücke 'P' zum Weiterspielen",120,100);
            }
        }
        else {

	    if(points == 404) points = -1;

            g.setColor(Color.black);
            g.fillRect(this.breakX+this.gpstart,this.breakY+this.gpstart,this.MAXW,this.MAXH);
            g.setColor(Color.white);
            g.setFont(new Font("Lucida Console",Font.BOLD,15));
            g.drawString("Spiel beendet. Du hast "+points+" Punkte erzielt! F2 zum Neustart",120,100);
        }
    }

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {

		if(status != 1 && e.getKeyCode() == 113) {

                    player.setX(this.gpstart);
                    player.setY(this.gpstart);
                    target.setY(MAXH+STDH);
                    target.setX(randIt(STDW,gpstart,MAXW));
                    setMark = 0;
                    toLeft = false;
                    toDown = false;
                    toRight = true;
                    level = 1;
                    ebene = 1;
                    points = 0;
                    status = 0;
                    repaint();
                }
	}

	public int getStatus() { return status; }
 }
