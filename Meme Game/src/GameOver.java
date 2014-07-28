
import jplay.*;
public class GameOver {
    
    public GameOver() throws InterruptedException{
       GameImage gameover = new GameImage("gameover2.png");
       Sprite desistir = new Sprite("desistir2.png",2);
       desistir.x=354;
       desistir.y=483;
       Sprite jogarnovamente = new Sprite("jogarnovamente2.png",2);
       jogarnovamente.x=6;
       jogarnovamente.y=483;
       int a=0;
       boolean gameoverroda=true;
       while(gameoverroda){
           System.out.println(Level.gameover.isExecuting());
 
           if(!Level.gameover.isExecuting()){
           if(Main.mouse.isLeftButtonPressed())
               a=1;
           else
               a=0;
           if (Main.mouse.isOverObject(jogarnovamente)){
               jogarnovamente.setCurrFrame(0);
               if(a==1){
                   for (int j=0;j<100;j++){
                       Level.inimigo[j] = null;
                   }
                   gameoverroda=false;
                   Level.vidas=5;
                   new Level();
               }
           }
           else
               jogarnovamente.setCurrFrame(1);
           
           if (Main.mouse.isOverObject(desistir)){
               desistir.setCurrFrame(0);
               if(a==1){
                   gameoverroda=false;
                   Main.janela.exit();
               }
           }
           else
               desistir.setCurrFrame(1);
           
           gameover.draw();
           desistir.draw();
           jogarnovamente.draw();
           Main.janela.update();
           Main.janela.delay(10);
           
           }
       }
       
       
    }
    
}
