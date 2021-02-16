 class Jump implements Runnable {

    Point obj;

    Jump(Point obj) {

        this.obj = obj;
    }

    public void run() {

        while(true) {

            try { Thread.sleep(1700); }
			catch(InterruptedException ie) {}

			CatchIt.Jump(obj);
        }
    }
 }
