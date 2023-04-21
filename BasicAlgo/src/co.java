public class co {

    static class  Apple{

        String taste;

        public Apple(){

        }

        public Apple(String str){
            this.taste = str;


        }

        public String getTaste(){
            return taste;
        }



    }



    public static void main(String[] args) {
        Apple apple1 = new Apple();
        Apple apple2 = new Apple("taste good");
        String apple2Taste = apple2.getTaste(); // return "taste good"
        String apple1Taste = apple1.getTaste(); // return null;
        System.out.println(apple1Taste);
        System.out.println(apple2Taste);
    }
}

