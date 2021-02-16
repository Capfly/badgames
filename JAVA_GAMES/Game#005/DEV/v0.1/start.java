 class start {

    public static void main(String[] args) {

	System.out.println("############################################################");
	System.out.println("# GAME BY Wolf-Jörgen Stange (Uni Potsdam # 768423 # 2013) #");
	System.out.println("############################################################");
	System.out.println();
	System.out.println("Starting Game...");

        CatchIt spiel = new CatchIt(760,480);

        new Thread(spiel).start();
    }
 }
