import java.awt.Color;

 class Point {

    private int x,y,w,h;
    private String name;
    private Color color;

    public Point(String name,Color color) {

        this.name = name;
        this.color = color;
        this.x = CatchIt.gpstart;
        this.y = CatchIt.gpstart;
        this.w = CatchIt.STDW;
        this.h = CatchIt.STDH;
    }
    public Point(String name) {

        this.name = name;
        this.color = Color.black;
        this.x = CatchIt.gpstart;
        this.y = CatchIt.gpstart;
        this.w = CatchIt.STDW;
        this.h = CatchIt.STDH;
    }
    public Point(int x,int y) {

        this.name = "Undefined Pixel";
        this.color = Color.black;
        this.x = CatchIt.gpstart+x;
        this.y = CatchIt.gpstart+y;
        this.w = CatchIt.STDW;
        this.h = CatchIt.STDH;
    }
    public Point(String name,int x,int y) {

        this.name = name;
        this.color = Color.black;
        this.x = CatchIt.gpstart+x;
        this.y = CatchIt.gpstart+y;
        this.w = CatchIt.STDW;
        this.h = CatchIt.STDH;
    }
    public Point(String name,Color color,int x,int y) {

        this.name = name;
        this.color = color;
        this.x = CatchIt.gpstart+x;
        this.y = CatchIt.gpstart+y;
        this.w = CatchIt.STDW;
        this.h = CatchIt.STDH;
    }
    public Point(int x,int y,int w,int h) {

        this.name = "Undefined Pixel";
        this.color = Color.black;
        this.x = CatchIt.gpstart+x;
        this.y = CatchIt.gpstart+y;
        this.w = w;
        this.h = h;
    }
    public Point(String name,int x,int y,int w,int h) {

        this.name = name;
        this.color = Color.black;
        this.x = CatchIt.gpstart+x;
        this.y = CatchIt.gpstart+y;
        this.w = w;
        this.h = h;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getW() { return this.w; }
    public int getH() { return this.h; }
    public Color getColor() { return this.color; }

    public void setX(int x) { this.x = CatchIt.gpstart+x; }
    public void setY(int y) { this.y = CatchIt.gpstart+y; }

    public void addToX(int add) {

        if(this.x+add >= CatchIt.gpstart && this.x+add < CatchIt.MAXW+CatchIt.gpstart) { this.x = (this.x+add); }
        else { System.out.println("Action not allowed!"); }

        //System.out.println("Added "+add+" to X.");
    }

    public void addToY(int add) {

        if(this.y+add >= CatchIt.gpstart && this.y+add < CatchIt.MAXH+CatchIt.gpstart) { this.y = (this.y+add); }
        else { System.out.println("Action not allowed!"); }
        //System.out.println("Added "+add+" to Y.");
    }

    public boolean checkCatch(Point p) {

        return (getX() == p.getX() && getY() == p.getY());
    }
 }
