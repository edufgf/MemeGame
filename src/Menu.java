import jplay.*;

public class Menu {
static GameImage menuintro,memegame;
static Sprite jogar,instrucoes,sair;
static int mscheck=1;
static Sound musicamenu=new Sound("menu.wav");
static Sound musicamenu2=new Sound("menu2.wav");

    public Menu() throws InterruptedException{
        menuintro = new GameImage("menu.png");
        memegame = new GameImage("memegame.png");
        memegame.x = 45;
        memegame.y = 45;
        Main.volumeicon.x =40+12+5;
        Main.volumeicon.y =648;
        jogar = new Sprite("jogar2.png",2);
        jogar.x = 200;
        jogar.y = 150;
        instrucoes = new Sprite("instrucoes.png",2);
        instrucoes.x = 200;
        instrucoes.y = 300;
        sair = new Sprite("sair.png",2);
        sair.x = 200;
        sair.y = 450;
        Main.mouse.setBehavior(Mouse.BUTTON_LEFT, Mouse.DETECT_EVERY_PRESS);
        musicamenu.play();
        if (Main.volumeicon.getTipoCreep()==0){
            musicamenu.volumeMute();
        }
        mscheck=1;
        int a=0;
        int MouseCheck2=1;
        Time SomTime = new Time(0,0,1,0,0,false);
        
        boolean somchanged=false;
        
        boolean menuroda=true;
       while(menuroda){
           if(Main.mouse.isLeftButtonPressed())
               a=1;
           else
               a=0;
           if (Main.mouse.isOverObject(jogar)){
               jogar.setCurrFrame(0);
               if(a==1){
                   menuroda=false;
                   musicamenu.stop();
                   musicamenu2.stop();
                   Level teste = new Level();
               }
           }
           else
               jogar.setCurrFrame(1);
           
           if (Main.mouse.isOverObject(instrucoes)){
               instrucoes.setCurrFrame(0);
               
               if(a==1){
                   
                   menuroda=false;
                   new Instrucoes();
                   
               }
           }
           else
               instrucoes.setCurrFrame(1);
           
           if (Main.mouse.isOverObject(sair)){
               sair.setCurrFrame(0);
               if(a==1){
                   menuroda=false;
                   Main.janela.exit();
               }
           }
           else
               sair.setCurrFrame(1);
           
           if (!musicamenu.isExecuting() && mscheck==1){
               mscheck=0;
               musicamenu2 = new Sound("menu2.wav");
               musicamenu2.play();
               musicamenu2.setRepeat(true);
               if (Main.volumeicon.getTipoCreep()==0){
                   musicamenu2.volumeMute();
               }
           }
if (Main.volumeicon.getTipoCreep()==0 && (!Main.mouse.isOverObject(Main.volumeicon)&&a==0)){
                   musicamenu2.volumeMute();
               }
   
           
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
                        musicamenu.volumeResume();
                        musicamenu2.volumeResume();              
                        }
                        if (somiconchk==1 && somchanged==false){
                        somchanged = true;
                        Main.volumeicon.setCreepTipo(0); 
                        
                        musicamenu.volumeMute();
                        musicamenu2.volumeMute();
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
            
           menuintro.draw();
           Main.volumeicon.draw();
           sair.draw();
           memegame.draw();
           instrucoes.draw();
           jogar.draw();
           Main.janela.update();
           Main.janela.delay(10);
           
       }
    }
}
