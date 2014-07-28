
import jplay.*;


public class Instrucoes {

    public Instrucoes() throws InterruptedException{
        Sprite instrucoes1 = new Sprite("instrucoes01.png");
        Sprite seta = new Sprite("seta.png",2);
        seta.x=375-(seta.width/2);
        seta.y =700-(seta.height);
        Time menu = new Time(1,0,0,0,0,false);;
        boolean instrroda=true;
         int a=0;
         int cont=0;
        int MouseCheck2 = 1;
        boolean somchanged=false;
        Time SomTime = new Time(0,0,1,0,0,false);
        
        while(instrroda){
            if(Main.mouse.isLeftButtonPressed())
               a=1;
           else
               a=0;
             if (!Menu.musicamenu.isExecuting() && Menu.mscheck==1){
               Menu.mscheck=0;
               Menu.musicamenu2 = new Sound("menu2.wav");
               Menu.musicamenu2.play();
               Menu.musicamenu2.setRepeat(true);
               if (Main.volumeicon.getTipoCreep()==0)
                   Menu.musicamenu2.volumeMute();
               if (Main.volumeicon.getTipoCreep()==1)
                   Menu.musicamenu2.volumeResume();
             }
               if (Main.volumeicon.getTipoCreep()==0 && (!Main.mouse.isOverObject(Main.volumeicon)&&a==0)){
                   Menu.musicamenu2.volumeMute();
               }
             System.out.println(menu.timeEnded());
             if (Main.mouse.isOverObject(seta)&& menu.timeEnded()){
               seta.setCurrFrame(0);
               
               if(a==1){
                   instrroda=false;
                   Menu.musicamenu.stop();
                   Menu.musicamenu2.stop();
                   Main.janela.delay(1000);
                    new Menu();
                 
               }
           }
           else
               seta.setCurrFrame(1);
     
              if (Main.mouse.isOverObject(seta)){
               seta.setCurrFrame(0);
               if(a==1&&cont==0){
                   cont=1;
                  instrucoes1 = new Sprite("instrucoes02.png");
                  seta = new Sprite("botaomenu.png",2);
                  seta.x=375-(seta.width/2);
                  seta.y =700-(seta.height)-20;

                  menu = new Time(0,0,1,0,0,false);
               }
           }
           else
               seta.setCurrFrame(1);
             
             
              //SOM MUTE / RESUME
           if(Main.mouse.isLeftButtonPressed())
               a=1;
           else
               a=0;
           if (SomTime.timeEnded())
               MouseCheck2=1;
           
            if (Main.mouse.isOverObject(Main.volumeicon)){
                Main.volumeicon.setCurrFrame(0);
                if (a==1&& MouseCheck2==1){
                        MouseCheck2=0;
                        int somiconchk = Main.volumeicon.getTipoCreep();
                        Main.volumeicon = new Sprite(Main.som[Main.volumeicon.getTipoCreep()],2);
                       
                        if (somiconchk==0 && somchanged==false){
                        somchanged = true;
                        Main.volumeicon.setCreepTipo(1);
                        Menu.musicamenu.volumeResume();
                        Menu.musicamenu2.volumeResume();          
                        }
                        if (somiconchk==1 && somchanged==false){
                        somchanged = true;
                        Main.volumeicon.setCreepTipo(0); 
                        
                        Menu.musicamenu.volumeMute();
                        Menu.musicamenu2.volumeMute();         
                        }           
                        Main.volumeicon.x =40+12+5;
                        Main.volumeicon.y =648;
                        Main.volumeicon.setCurrFrame(0);
                        SomTime = new Time(0,0,1,0,0,false);
                        somchanged = false;
                }
            }
            else {
                Main.volumeicon.setCurrFrame(1);
            }
              
            //DESENHANDO  
            Menu.menuintro.draw();
            instrucoes1.draw();
            Main.volumeicon.draw();
            seta.draw();
            Main.janela.update();
            Main.janela.delay(10);
            
        }
    }
}
