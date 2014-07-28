
import java.awt.Color;
import java.awt.Font;
import jplay.*;

public class Level {
    public static int lvl=0, vidas = 5,score =0, MouseCheck, bosslife,bonusget,bonusvida;
    public static float centrocampo_x , centrocampo_y;
    public static float[] Lx , Ly;
    public static Sprite[] inimigo = new Sprite[300];
    public static Sprite[] creep_tipo = new Sprite[10];
    public static Sprite[] lifes = new Sprite[15];
    public static Sprite[] minipedo = new Sprite[3];
    public static Sprite coracao_manolo,bossvida_label,manolo,barraespecial;
    public static GameImage levelind2,campo,pause;
    public static Sound gameover,win,musica2;
    public static Time PauseTime;
    public static Animation campobonus,campobonus2,campopisca; 
    public static boolean pausechanged,execpause;
            
            
    
    
public Level() throws InterruptedException {
      
    //Algumas definições iniciais 
     LimpaVetorInimigos();
     Animation barraespecial = new Animation("barraespecial2.png",12);
     barraespecial.y=255;
     barraespecial.x=12;
     barraespecial.setLoop(false);
     Animation pow = new Animation("pow4.png",2);
     Animation go = new Animation("go.png",3);
     Animation regressiva = new Animation("regressiva.png",26);
     regressiva.setTotalDuration(5200);
     regressiva.setLoop(false);
     regressiva.x = 363;
     regressiva.y = 130;
     int gol =1;
     int c=0;
     int manolo_tipo=0;
     
     
     campo = new GameImage(Main.cenario[lvl]);
     
     
     
     levelind2 = new GameImage(Main.levelind[lvl]); 
     levelind2.x=12;
     levelind2.y=35;
     
     pause = new GameImage("pausado.png");
     
     Sprite pedoboss =  new Sprite("pedoboss.png",2);
     pedoboss.x=campo.width/2+Main.espaco_x1 - (pedoboss.width/2);
     pedoboss.y=Main.espaco_y1-pedoboss.height;
     pedoboss.setCurrFrame(0);
     
     creep_tipo[0] = new Sprite(Main.creep[0]);
     creep_tipo[1] = new Sprite(Main.creep[1]);
     
     
     int bossbateu =0;
     
     coracao_manolo = new Sprite("coracaomanolo.png");
     Sprite coracao_forever = new Sprite("foreveralonecoracao.png");
     Sprite megusta = new Sprite("megusta.png",2);
     
     Sprite manolo_bonus = null;
     
     for (int v=0;v<15;v++){
         lifes[v] = new Sprite("vidas.png"); 
         lifes[v].x=15+ 20*v +8*v;
         lifes[v].y=140;
     }
     
     bossvida_label = new Sprite("bossvidalabel.png");
     bossvida_label.x=180;
     bossvida_label.y=15;
     for (int z=0;z<3;z++){
         minipedo[z] = new Sprite("minipedoboss.png"); 
         minipedo[z].x=170+ 15+ 20*z +8*z;
         minipedo[z].y=45;
     }
     bosslife=3;
     manolo = new Sprite("manolo03.png",2);
     Lx = new float[4]; 
     Ly = new float[4];

     Main.pauseicon.x =12;
     Main.pauseicon.y =648;
     
     
     float manolo_vel,inimigo_vel;
     centrocampo_x = campo.width/2+Main.espaco_x1 - manolo.width/2;
     centrocampo_y = campo.height/2+Main.espaco_y1 - manolo.height/2;
     campo.x = 700-campo.width-10;
     campo.y = 700-campo.height-74;

     manolo.x = centrocampo_x;
     manolo.y = centrocampo_y;
     coracao_manolo.x=manolo.x;
     coracao_manolo.y=manolo.y;
     inimigo_vel =1;
     manolo_vel = 3;
    //BONUS 
     if (lvl==10){
     campopisca = new Animation("cenariobonuspisca.png",9);
     campobonus2 = new Animation("cenariobonus.png",11);
     campobonus = new Animation("cenariobonus.png",11);
     campobonus.setTotalDuration(17480);
     campobonus.setLoop(false);
     bonusvida=vidas;
     campobonus.x=campo.x;
     campobonus.y=campo.y;
     campobonus2.x=campo.x;
     campobonus2.y=campo.y;
     campopisca.x=campo.x;
     campopisca.y=campo.y;
     }
     
     
     Time intro = new Time(0,0,Main.intromusictime[lvl],0,0,false);
     Time especial = new Time(0,0,Main.intromusictime[lvl]+Main.especialrespawn[lvl],0,0,false);
     Time contregressiva = new Time(0,0,Main.intromusictime[lvl]-5,0,0,false);
     Time spawn = new Time(0,0,Main.intromusictime[lvl],0,0,false);
     Time especiallast = new Time(1,0,0,0,0,false);
     Time barraespecialtime = new Time(1,0,0,0,0,false);
     Time SomTime = new Time(0,0,1,0,0,false);
     PauseTime = new Time(0,0,1,0,0,false);
     int mscheck =1;
     int spawncheck=1;
     int i = 0;
     int h =0;
     int killed=0;
     int velup=0;
     boolean powcheck = false;
     boolean especialcheck = false;
     float inimigox,inimigox1,powx,powy,powx1,powy1,manoloposx,manoloposy;
     boolean barraespecialcheck=false;
     float pedobossy=(Main.espaco_y1 + inimigo_vel+3);
     float pedobossx=(float) pedoboss.x;
     int MouseCheck =0 , MouseCheck2 =1;
     boolean somchanged = false;
     pausechanged = false;
     execpause = false;
     boolean campocheck=false , campocheck2=false;
     boolean bonusending=false;
     int g;
     int bonuscont=0;
     int TipoCreepColidido;
     
     
     //CONVERSA ANTES
     if (lvl==0&& Conversa.globalcont==0)
        new Conversa();
     if (lvl==4&& Conversa.globalcont==1)
        new Conversa();
     if (lvl==9&& Conversa.globalcont==2)
        new Conversa();
     if (lvl==10&& Conversa.globalcont==3)
         new Conversa();
     if (lvl==11&& Conversa.globalcont==4)
         new Conversa();
     
     Sound musica,musica2;
     win = new Sound("nextlevel.wav");
     gameover = new Sound("gameover.wav");
     musica2 = new Sound(Main.musicloop[lvl]);
     musica = new Sound(Main.musicintro[lvl]);
     musica.play();
     if (Main.volumeicon.getTipoCreep()==0){
         musica.volumeMute();
     }
     
     
     boolean exec=true;
        
        
     
     //WHILE BEGINS
            while(exec){

             
    //COMEÇA A RODADA,GERA POSIÇÃO ALEATORIA DO INIMIGO
             if(intro.timeEnded()&& spawncheck ==1){
                 spawncheck=0;
                inimigos(0);
                if (lvl ==9){
                pedoboss.setCurrFrame(1);
                 pedobossx =(Main.espaco_x1+inimigo_vel);
                }
              }
            
    //CRIANDO INIMIGOS DE CADA LEVEL         
                if (spawn.timeEnded()){
                    if (manolo_tipo !=3 && vidas!=0) {
                        
                    if (lvl ==0||lvl==1){
                        
                    spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl]){
                             CriarCreep(i,h,0);
                        if (h==3)
                            h=-1;
                            inimigos(0);
                         }
                        i++;
                        h++;
                  }
                    
                  
                    if (lvl ==2 || lvl==3){
                        
                    spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl]){
                        CriarCreep(i,h,0);
                        i++;
                        inimigos(0);
                        CriarCreep(i,h,0);
                        if (h==3)
                            h=-1;
                            inimigos(0);
                         }
                        i++;
                        h++;
                  }
                    
                    if (lvl ==4){                 
                        spawn.setSecond(Main.inimigorespawn[lvl]);   
                         if (i < Main.qtdinimigoslevel[lvl]){
                        CriarCreep(i,h,1);
                        if (h==3)
                            h=-1;
                            inimigos(1);
                         }
                        i++;
                        h++;
                  }
                   if (lvl ==5){
                        
                    spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl]){
                        CriarCreep(i,h,0);
                        i++;
                        inimigos(1);
                        CriarCreep(i,h,1);
                        if (h==3)
                            h=-1;
                            inimigos(0);
                         }
                        i++;
                        h++;
                  }
                   if (lvl ==6){
                        
                    spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl]){
                           if (i<Main.qtdinimigoslevel[lvl]-1){
                        CriarCreep(i,h,0);
                        i++;
                        inimigos(0);
                        CriarCreep(i,h,0);
                                 }
                        if (i!=1){
                        i++;
                      
                        inimigos(1);
                        inimigo[i] = new Sprite(Main.creep[1],2);
                        inimigo[i].setCreepTipo(1);
                        inimigo[i].setCurrFrame(0);
                        if (h!=0){
                        inimigo[i].x = Lx[h-1];
                        inimigo[i].y = Ly[h-1];
                        }else {
                           inimigo[i].x = Lx[3];
                           inimigo[i].y = Ly[3]; 
                        }
                        }
                        if (h==3)
                            h=-1;
                            inimigos(0);
                         }
                        i++;
                        h++;
                  } 
                  
                  if (lvl ==7){
                        
                    spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl]){
                        CriarCreep(i,h,0);
                        i++;
                        inimigos(0);
                        CriarCreep(i,h,0);
                        i++;
                      
                        inimigos(1);
                        CriarCreep(i,h,1);
                        if (h==3)
                            h=-1;
                            inimigos(0);
                         }
                        i++;
                        h++;
                  }
                  
                    if (lvl ==8){
                        
                    spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl]){
                        CriarCreep(i,h,0);
                        i++;
                        inimigos(0);
                        CriarCreep(i,h,0);
                        i++;
                      
                        inimigos(1);
                        CriarCreep(i,h,1);
                        i++;
                        inimigos(1);
                        CriarCreep(i,h,1);
                        if (h==3)
                            h=-1;
                            inimigos(0);
                         }
                        i++;
                        h++;
                  }
                    if (lvl==9){
                      spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl]){
                        inimigo[i] = new Sprite(Main.creep[0],2);
                        inimigo[i].setCreepTipo(0);
                        inimigo[i].setCurrFrame(0);
                        inimigo[i].x = pedoboss.x+(pedoboss.width/2);
                        inimigo[i].y = pedoboss.y+(pedoboss.height);
                        if ((i==4||i==10||i==16||i==22)){    
                        i++;
                        inimigo[i] = new Sprite(Main.creep[1],2);
                        inimigo[i].setCreepTipo(1);
                        inimigo[i].setCurrFrame(0);
                        inimigo[i].x = pedoboss.x+(pedoboss.width/2);
                        inimigo[i].y = pedoboss.y+(pedoboss.height);
                        }
                        if ((i==26||i==30||i==34||i==38||i==42||i==46)){    
                        i++;
                        inimigo[i] = new Sprite(Main.creep[1],2);
                        inimigo[i].setCreepTipo(1);
                        inimigo[i].setCurrFrame(0);
                        inimigo[i].x = pedoboss.x+(pedoboss.width/2);
                        inimigo[i].y = pedoboss.y+(pedoboss.height);
                        }
                        if (i >= 50){    
                        i++;
                        inimigo[i] = new Sprite(Main.creep[1],2);
                        inimigo[i].setCreepTipo(1);
                        inimigo[i].setCurrFrame(0);
                        inimigo[i].x = pedoboss.x+(pedoboss.width/2);
                        inimigo[i].y = pedoboss.y+(pedoboss.height);
                        }
                        i++;
                    }
                    
                    }
                     if (lvl ==10){
                         
                         
                         
                    if (i==4)
                        Main.inimigorespawn[lvl]=4;
                    if (i==19)     
                        Main.inimigorespawn[lvl]=2;
                    if (i==44)
                        Main.inimigorespawn[lvl]=1;
                    if (i==76){
                        Main.vel[0]=3.0f;
                        Main.vel[1]=4.0f;
                    }
                    
                    
                    //Cria Bonus
                         System.out.println(i);
                    if(bonusending==true&&i<255){
                        int x=0,y=0,z=0,w=0;
                        for (i=136;i<246;i++){
                            if (i<155){
                               inimigo[i] = new Sprite(Main.creep[0],2);
                               inimigo[i].setCreepTipo(0);
                               inimigo[i].setCurrFrame(0);
                               inimigo[i].setCreepLado(0);
                               inimigo[i].x = Main.espaco_x1-inimigo[i].width;
                               inimigo[i].y = Main.espaco_y1+x*inimigo[i].height;
                               x++;
                            }
                            if (i>=155&&i<174){
                               inimigo[i] = new Sprite(Main.creep[0],2);
                               inimigo[i].setCreepTipo(0);
                               inimigo[i].setCurrFrame(1);
                               inimigo[i].setCreepLado(2);
                               inimigo[i].x = 750-Main.espaco_x2+inimigo[i].width;
                               inimigo[i].y = Main.espaco_y1+z*inimigo[i].height;
                               z++;
                            

                            }
                            if (i>=174&&i<210){
                               inimigo[i] = new Sprite(Main.creep[0],2);
                               inimigo[i].setCreepTipo(0);
                               inimigo[i].setCurrFrame(0);
                               inimigo[i].setCreepLado(1);
                               inimigo[i].x = Main.espaco_x1+y*(inimigo[i].width+10);
                               inimigo[i].y = Main.espaco_y1-inimigo[i].height;
                               y++;
                            }
                            if (i>=210&&i<246){
                               inimigo[i] = new Sprite(Main.creep[0],2);
                               inimigo[i].setCreepTipo(0);
                               inimigo[i].setCurrFrame(1);
                               inimigo[i].setCreepLado(3);
                               inimigo[i].x = Main.espaco_x1+w*(inimigo[i].width+10);
                               inimigo[i].y = 700-Main.espaco_y2+inimigo[i].height;
                               w++;
                            }
                            
                            
                            
                            
                        }
                        Main.inimigorespawn[lvl]=10;
                        i=256;
                            
                    }
                    
                    if (i==22||i==110 || i==257){
                        bonuscont++;
                        manolo_bonus = new Sprite("manolo03.png",2);
                        if (i==22){
                        manolo_bonus.x = Lx[2];
                        manolo_bonus.y = Ly[2];
                        }
                        if (i==110){
                        manolo_bonus.x = Lx[0];
                        manolo_bonus.y = Ly[0];   
                        }
                        if (i==257){
                        manolo_bonus.x = centrocampo_x;
                        manolo_bonus.y = centrocampo_y-manolo_bonus.height- campo.height/2;   
                        }
                        inimigos(0);
                    }
                    if (i==250){
                        bonusending =true;  
                        Main.inimigorespawn[lvl]=6;
                        }
                    
                    
                    spawn.setSecond(Main.inimigorespawn[lvl]);
                         
                         
                         if (i < Main.qtdinimigoslevel[lvl] && !bonusending){
                             CriarCreep(i,h,0);
                         if (i>=8){    
                             i++;
                             inimigos(0);
                             CriarCreep(i,h,0);
                         }
                         if (i>=16){
                             i++;
                             inimigos(1);
                             CriarCreep(i,h,1);
                         }
                         if (i>=28){
                             i++;
                             inimigos(1);
                             CriarCreep(i,h,1);
                         }
                         if (i>=94){
                             g = h;
                             if (g==2)
                                 g=-2;
                             if(g==3)
                                 g=-1;
                             i++;
                             CriarCreep(i,g+2,0);
                             i++;
                             inimigos(0);
                             CriarCreep(i,g+2,0);
                             i++;
                             inimigos(1);
                             CriarCreep(i,g+2,1);
                             i++;
                             inimigos(1);
                             CriarCreep(i,g+2,1);
                         if (i>=106){  
                             i++;
                             inimigos(0);
                             CriarCreep(i,h,0); 
                             i++;
                             inimigos(1);
                             CriarCreep(i,h,1);
                             
                             
                         }              
                         }
                         
                         
                         
                        if (h==3)
                            h=-1;
                            inimigos(0);
                         }
                        i++;
                        h++;
                  }
                    
                    
                 }
                }
    
     //MUSICA LOOP       
            if  (!musica.isExecuting() && mscheck ==1){
                mscheck=0;
                musica2.play();
                musica2.setRepeat(true);
                if (Main.volumeicon.getTipoCreep()==0){
                        musica2.volumeMute();
                  }else{
                    musica2.volumeResume();
                }
             }
               if (Main.volumeicon.getTipoCreep()==0 && (!Main.mouse.isOverObject(Main.volumeicon)&&MouseCheck==0)){
                   musica2.volumeMute();
               }
               if (Main.volumeicon.getTipoCreep()==1 && (!Main.mouse.isOverObject(Main.volumeicon)&&MouseCheck==0)){
                   musica2.volumeResume();
               }
                
                            
     //MANOLO ANIMAÇÃO       
            if (Main.teclado.keyDown(Keyboard.LEFT_KEY)){
                manolo.setCurrFrame(1);
            }
            if (Main.teclado.keyDown(Keyboard.RIGHT_KEY)){
                manolo.setCurrFrame(0);
            }
     //VELOCIDADE MANOLO       
            if (regressiva.isPlaying()==false&&vidas!=0){
            manolo.moveX(manolo_vel);
            manolo.moveY(manolo_vel);
            }
            
     //GAME OVER
            if (vidas==0){
                megusta.x = manolo.x;
                megusta.y = manolo.y;
                manolo = megusta;
                manolo.draw();
                Main.janela.update(); 
                musica.stop();
                musica2.stop();
                if (lvl!=10){
                lvl=0;
                gameover.play();
                Main.janela.delay(1000);
                score=0;
                new GameOver();
                exec=false;
                }
                gameover.play();
                Main.janela.delay(1000);
                exec=false;
                while(gameover.isExecuting()){
                    System.out.println(gameover.isExecuting());
                }
                vidas = bonusvida;
                killed=0;
                lvl++;
                new Level();
            }
            
            
      //DEFINI A POSIÇÃO DO MANOLO E SEUS COMPLEMENTARES      
            coracao_manolo.x=manolo.x+ (manolo.width/2 - coracao_manolo.width/2);
            coracao_manolo.y=manolo.y + (manolo.height/2 - coracao_manolo.height/2);
            coracao_forever.x =manolo.x+ (manolo.width/2 - coracao_forever.width/2);
            coracao_forever.y=manolo.y + (manolo.height/2 - coracao_forever.height/2);
            campo.draw();
            
            if (lvl==10){
                campobonus.draw();
                campobonus.update();
                if (!campobonus.isPlaying()&&campocheck==false){
                    campocheck=true;
                    campobonus2.setTotalDuration(1600);
                    campobonus2.setLoop(false);
                }
                if (campocheck==true){
                    campobonus2.draw();
                    campobonus2.update();
                    if (!campobonus2.isPlaying()&&campocheck2==false){
                    campocheck2=true;    
                    campopisca.setTotalDuration(4500);
                    campopisca.setLoop(true);
                }
                }
                if (campocheck2==true){
                    campopisca.draw();
                    campopisca.update();
                }
            }
            manolo.draw();
            if (manolo_bonus !=null){
                     if (bonuscont==1){
                      manolo_bonus.moveTo(manolo_bonus.x-campo.width-manolo_bonus.width, manolo_bonus.y, 3.0);
                      manolo_bonus.setCurrFrame(1);
                 }
                     if (bonuscont==2){
                      manolo_bonus.moveTo(manolo_bonus.x+campo.width+manolo_bonus.width, manolo_bonus.y, 3.0);
                      manolo_bonus.setCurrFrame(0);
                 }
                     if (bonuscont==3){
                      manolo_bonus.moveTo(manolo_bonus.x, manolo_bonus.y+campo.height+manolo_bonus.height, 3.0);
                      manolo_bonus.setCurrFrame(0);
                 }
                     manolo_bonus.draw();
                     if (manolo_bonus.collided(manolo)){
                         manolo_bonus=null;
                         pow = new Animation("powbonus.png",5);
                         pow.x = manolo.x;
                         pow.y = manolo.y;
                         pow.setTotalDuration(100);
                         pow.setLoop(false);
                         powcheck = true;
                         bonusget++;
                         if (bonusget==3){
                             killed =246;
                         }
                     }
                 } 
          
         
      //DESENHA INIMIGO E TRATA COLISÕES
            for (int j=0;j<300;j++){
            if (inimigo[j] != null && vidas !=0){
                
                
                inimigox = (float) inimigo[j].x;
                if(manolo_tipo != 3&& vidas!=0 && inimigo[j].getTipoCreep() !=1 && lvl!=10)
                    inimigo[j].moveTo(manolo.x+ (manolo.width/2 - inimigo[j].width/2), manolo.y + (manolo.height/2 - inimigo[j].height/2),inimigo_vel);
                else  if (manolo_tipo != 3&& vidas!=0 && inimigo[j].getTipoCreep() == 1 && velup==0&& lvl!=10)
                    inimigo[j].moveTo(manolo.x+ (manolo.width/2 - inimigo[j].width/2), manolo.y + (manolo.height/2 - inimigo[j].height/2),inimigo_vel*1.5);
                if (manolo_tipo != 3&& vidas!=0 && inimigo[j].getTipoCreep() == 1 && velup==1&& lvl!=10)
                    inimigo[j].moveTo(manolo.x+ (manolo.width/2 - inimigo[j].width/2), manolo.y + (manolo.height/2 - inimigo[j].height/2),1.8);
                if (manolo_tipo != 3&& vidas!=0 && inimigo[j].getTipoCreep() == 1 && velup==2&& lvl!=10)
                    inimigo[j].moveTo(manolo.x+ (manolo.width/2 - inimigo[j].width/2), manolo.y + (manolo.height/2 - inimigo[j].height/2),1.9);
              
                if (lvl==10){

                 if (inimigo[j].getCreepLado()==0) {     
                 inimigo[j].moveTo(inimigo[j].x+campo.width+inimigo[j].width, inimigo[j].y, Main.vel[inimigo[j].getTipoCreep()]);
                 inimigo[j].setCurrFrame(0);
                 }
                 if (inimigo[j].getCreepLado()==1) {     
                     inimigo[j].moveTo(inimigo[j].x, inimigo[j].y+campo.height+inimigo[j].height, Main.vel[inimigo[j].getTipoCreep()]);
                     inimigo[j].setCurrFrame(0);
                 }
                 if (inimigo[j].getCreepLado()==2) {    
                     inimigo[j].setCurrFrame(1);
                     inimigo[j].moveTo(inimigo[j].x-campo.width-inimigo[j].width, inimigo[j].y, Main.vel[inimigo[j].getTipoCreep()]);
                 }
                 if (inimigo[j].getCreepLado()==3) {   
                     inimigo[j].setCurrFrame(1);  
                     inimigo[j].moveTo(inimigo[j].x, inimigo[j].y-campo.height-inimigo[j].height, Main.vel[inimigo[j].getTipoCreep()]);                      
                 }
                }
              inimigo[j].draw();
                
                if (inimigox > inimigo[j].x && manolo.x != inimigo[j].x && lvl!=10){
                    inimigo[j].setCurrFrame(1);
                }else{
                    inimigo[j].setCurrFrame(0);
                }
                
                for (int y=0;y<300;y++){
                    if (inimigo[y]!=null&&lvl!=10){
                        if (inimigo[j].collided(inimigo[y]) && (inimigo[j].getTipoCreep() == inimigo[y].getTipoCreep())){
                         if ((inimigo[j].x+inimigo[j].width-inimigo_vel-5) < inimigo[y].x)
                               inimigo[j].x = inimigo[y].x - inimigo[j].width;
                         if (inimigo[j].y+inimigo[j].height-inimigo_vel-5 < inimigo[y].y)
                             inimigo[j].y = inimigo[y].y - inimigo[j].height;
                         if (inimigo[j].x > inimigo[y].x + inimigo[y].width-inimigo_vel-5)
                               inimigo[j].x = inimigo[y].x +inimigo[y].width;
                           if (inimigo[j].y > inimigo[y].y+inimigo[y].height-inimigo_vel-5)
                                  inimigo[j].y = inimigo[y].y+inimigo[y].height;
                         
                         
                        }
                    }
                }
                
                if (inimigo[j].collided(coracao_forever)&& manolo_tipo==4) {

                if ((inimigo[j].x+inimigo[j].width-inimigo_vel-5) < coracao_forever.x)
                     inimigo[j].x = coracao_forever.x - inimigo[j].width;
                if (inimigo[j].y+inimigo[j].height-inimigo_vel-5 < coracao_forever.y)
                  inimigo[j].y = coracao_forever.y - inimigo[j].height;
                if (inimigo[j].x > coracao_forever.x + coracao_forever.width-inimigo_vel-5)
                    inimigo[j].x = coracao_forever.x +coracao_forever.width;
                if (inimigo[j].y > coracao_forever.y+coracao_forever.height-inimigo_vel-5)
                    inimigo[j].y = coracao_forever.y+coracao_forever.height;
                }
                if (inimigo[j].collided(coracao_forever)&& manolo_tipo==5) {
                 inimigo[j].moveTo(manolo.x+ (manolo.width/2 - inimigo[j].width/2), manolo.y + (manolo.height/2 - inimigo[j].height/2),3*inimigo_vel);
                   inimigox1 = (float) inimigo[j].x;
                if (inimigox1 > inimigo[j].x && manolo.x != inimigo[j].x){
                    inimigo[j].setCurrFrame(0);
                }else{
                    inimigo[j].setCurrFrame(1);
                }
               }
            
                
                if (inimigo[j].collided(coracao_manolo)){
                    powx= (float) inimigo[j].x;
                    powy= (float) inimigo[j].y;
                    TipoCreepColidido = inimigo[j].getTipoCreep();
                   if (manolo_tipo == 0){
                   inimigo[j]=null;
                   pow = new Animation("pow5.png",5);
                   pow.x = manolo.x;
                   pow.y = manolo.y;
                   pow.setTotalDuration(100);
                   pow.setLoop(false);
                   powcheck = true;
                   vidas--;
                   killed++;
                   }
                   if (manolo_tipo == 1){
                   inimigo[j]=null;
                   pow = new Animation("pow4.png",5);
                   pow.x = powx;
                   pow.y = powy;
                   pow.setTotalDuration(100);
                   pow.setLoop(false);
                   powcheck = true;
                   score+=Main.valor[TipoCreepColidido];
                   killed++;
                   }
                   if (manolo_tipo == 2){
                       
                   }
                   
                   if (manolo_tipo == 3){
                   inimigo[j]=null;
                   pow = new Animation("pow5.png",5);
                   pow.x = manolo.x;
                   pow.y = manolo.y;
                   pow.setTotalDuration(100);
                   pow.setLoop(false);
                   powcheck = true;
                   vidas--;
                   killed++;
                   }
                if (manolo_tipo == 5){
                   inimigo[j]=null;
                   pow = new Animation("pow4.png",5);
                   pow.x = powx;
                   pow.y = powy;
                   pow.setTotalDuration(100);
                   pow.setLoop(false);
                   powcheck = true;
                   score+=Main.valor[TipoCreepColidido];
                   killed++;
                   }
                   
                }
                
                if(manolo_tipo==6){
                   TipoCreepColidido = inimigo[j].getTipoCreep(); 
                   if(inimigo[j].x>166 && inimigo[j].x <690-inimigo[j].width && inimigo[j].y >104 && inimigo[j].y < 625-inimigo[j].height){
                  
                   powx1= (float) inimigo[j].x;
                   powy1= (float) inimigo[j].y;
                   inimigo[j]=null;
                   pow = new Animation("pow4.png",5);
                   pow.x = powx1;
                   pow.y = powy1;
                   pow.setTotalDuration(100);
                   pow.setLoop(false);
                   powcheck = true;
                   score+=Main.valor[TipoCreepColidido];
                   killed++;
                    }
                }
                
                
                
            }
            }
            
            //BOSS - CRIA E DESENHA INIMIGOS
             if (lvl==9){
                pedoboss.draw();
                for (int z=0;z<bosslife;z++){
                   minipedo[z].draw();
            }
                if(manolo_tipo!=3)
                pedoboss.moveTo(pedobossx, pedobossy, inimigo_vel);
               
            }
             
             if (intro.timeEnded()&&lvl==9){
                 
                 if (killed>=24&& killed<50 &&velup==0){
                   velup=1;
                   inimigo_vel=(float) 1.4;
                   pow = new Animation("powboss.png",5);
                   pow.x = pedoboss.x+ (pedoboss.width/2 - pow.width/2);
                   pow.y=pedoboss.y + (pedoboss.height/2 - pow.height/2);
                   pow.setTotalDuration(100);
                   pow.setLoop(false);
                   powcheck = true;
                   bosslife--;
                    }
                 if (killed>=50 && killed<80 && velup==1){
                     velup=2;
                     inimigo_vel=(float) 1.8;
                     pow = new Animation("powboss.png",5);
                     pow.x = pedoboss.x+ (pedoboss.width/2 - pow.width/2);
                     pow.y=pedoboss.y + (pedoboss.height/2 - pow.height/2);
                     pow.setTotalDuration(100);
                     pow.setLoop(false);
                     powcheck = true;
                     bosslife--;
                 }
                 if(killed>=80 && velup==2){
                     velup=3;
                     pow = new Animation("powboss.png",5);
                     pow.x = pedoboss.x+ (pedoboss.width/2 - pow.width/2);
                     pow.y=pedoboss.y + (pedoboss.height/2 - pow.height/2);
                     pow.setTotalDuration(100);
                     pow.setLoop(false);
                     powcheck = true;
                     pow.update();
                     pow.draw();
                     bosslife--;
                     exec=false;
                     musica2.stop();
                     Main.janela.update(); 
                
                manolo.x=centrocampo_x;
                manolo.y = centrocampo_y;
                pedoboss.x=campo.width/2+Main.espaco_x1 - (pedoboss.width/2);
                pedoboss.y=Main.espaco_y1+inimigo_vel+3;
            
            campo.draw();    
            Main.background.draw();
            Main.volumeicon.draw();
            Main.pauseicon.draw();
            levelind2.draw();
            
            if(lvl==9){
                bossvida_label.draw();
            for (int z=0;z<bosslife;z++){
                   minipedo[z].draw();
            }
            }
            for (int v=0;v<vidas;v++){
                lifes[v].draw();
            }
           Main.janela.drawText(Integer.toString(score), 15, 210, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
           Main.pauseicon.draw();
           pedoboss.draw();
                manolo.draw(); 
                win.play();
                score+=1000;
                Main.janela.update(); 
                Main.janela.delay(1000);
                lvl++;
                killed=0;
                while(win.isExecuting()){
                    System.out.println(win.isExecuting());
                }
                 new Conversa();    
                     
                 }
                 if (pedoboss.x <= Main.espaco_x1+inimigo_vel && bossbateu==0){
                     pedobossx= ((750-Main.espaco_x2)-inimigo_vel);
                     bossbateu=1;
                     pedoboss.setCurrFrame(0);
 
                 }

                 if (pedoboss.x+pedoboss.width >= (750-Main.espaco_x2)-inimigo_vel && bossbateu==1 ){
                     pedobossx= (Main.espaco_x1+inimigo_vel);
                     bossbateu=0;
                     pedoboss.setCurrFrame(1);
             }  
                 if (pedoboss.collided(manolo)&& manolo_tipo !=2){
                   manolo.x = centrocampo_x;
                   manolo.y = centrocampo_y;
                   pow = new Animation("pow5.png",5);
                   pow.x = manolo.x;
                   pow.y = manolo.y;
                   pow.setTotalDuration(100);
                   pow.setLoop(false);
                   powcheck = true;
                   vidas--;
                 }
                   
             }
                 
    //ANIMAÇÃO 'GO' DO COMEÇO         
             if (contregressiva.timeEnded()){
             regressiva.draw();
             regressiva.update(); 
             if (regressiva.isPlaying()==false){
                 regressiva.hide();
                 if (gol==1){
                     gol=0;
                 go = new Animation("go.png",2);
                 go.setDuration(0, 1500);
                 go.setLoop(false);
                 go.x=300;
                 go.y=100;
                 }
                 go.draw();
                 go.update();
             }
            }
             
     //CRIA ITEM ESPECIAL NO CAMPO
            if (especial.timeEnded()&&especialcheck==false&&vidas!=0){
                      c=(int) (Math.random()*6+1);
                      Main.especiais[c]= new Sprite(Main.especiais_string[c],2);
                      Main.especiais[c].x = (Main.espaco_x1 + (Math.random()*(524 - Main.especiais[c].width)));
                      if (lvl!=9){
                           Main.especiais[c].y= (Main.espaco_y1 + (Math.random()*(521-Main.especiais[c].height)));  
                      }else{
                           Main.especiais[c].y= (Main.espaco_y1+pedoboss.height + (Math.random()*(521-pedoboss.height-Main.especiais[c].height))); 
                      }
                      Main.especiais[c].draw();
                      especialcheck=true;
                      especial.setSecond(Main.especialrespawn[lvl]+Main.especialrespawn[lvl]/2);
                      if (manolo.collided(Main.especiais[c])){
                        manolo = Main.especiais[c];
                        manolo_tipo = c;
                        }
                    }
            
      //DESENHA ITEM ESPECIAL NO CAMPO E SETA PROXIMA RESPAWN E DURAÇÃO
            if (especialcheck ==true){
               Main.especiais[c].draw();
               if (especial.timeEnded()){
                   especialcheck=false;
               }
                      if (manolo.collided(Main.especiais[c])){
                        especialcheck =false;
                        //respawn especial ao mesmo tempo,mais rapido.No meio de um efeito, surge outro especial
                        //especial.setSecond(Main.especialrespawn[lvl]+Main.especialduration[c]/2);
                        //Respawn de especial após o efeito de um. + tempo do respawn padrao.
                        especial.setSecond(Main.especialrespawn[lvl]+Main.especialduration[c]);
                        especiallast = new Time(0,0,Main.especialduration[c],0,0,false);
                        manolo = Main.especiais[c];
                        manolo_tipo = c; 
                        
                        barraespecial = new Animation("barraespecial3.png",11);
                        barraespecial.y=255;
                        barraespecial.x=12;
                        barraespecial.setLoop(false);
                        barraespecial.setTotalDuration(1000*Main.especialduration[c]);
                        barraespecialtime = new Time(0,0,Main.especialduration[c],0,0,false);
                        barraespecialcheck=true;
                        }
                    }  
     //FIM DO EFEITO ESPECIAL, CRIA MANOLO PADRAO        
            if (especiallast.timeEnded()){
                especiallast.setHour(100);
                manoloposx=(float) manolo.x;
                manoloposy=(float) manolo.y;
                manolo = new Sprite("manolo03.png",2);
                manolo.x=manoloposx;
                manolo.y=manoloposy;
                manolo_tipo=0;
                
                
            }
     
   //LIMITES DO CENARIO PRO MANOLO PADRAO       
            if (manolo.x < Main.espaco_x1)
                    manolo.x = manolo.x +manolo_vel;
            if (manolo.y < Main.espaco_y1)
                 manolo.y = manolo.y +manolo_vel;
            if (manolo.x + manolo.width > 750 - Main.espaco_x2)
                 manolo.x = manolo.x -manolo_vel;
            if (manolo.y + manolo.height > 700 - Main.espaco_y2)
                 manolo.y = manolo.y -manolo_vel;
   
   //ANIMAÇÃO DO SOM E MUTE        
            if(Main.mouse.isLeftButtonPressed())
               MouseCheck=1;
           else
               MouseCheck=0;
           if (SomTime.timeEnded())
               MouseCheck2=1;
           
            if (Main.mouse.isOverObject(Main.volumeicon)){
                Main.volumeicon.setCurrFrame(0);
                if (MouseCheck==1&&MouseCheck2==1){
                        MouseCheck2=0;
                        int somiconchk = Main.volumeicon.getTipoCreep();
                        Main.volumeicon = new Sprite(Main.som[Main.volumeicon.getTipoCreep()],2);
                       
                        if (somiconchk==0 && somchanged==false){
                        somchanged = true;
                        Main.volumeicon.setCreepTipo(1);
                        musica.volumeResume();
                        musica2.volumeResume();
                           
                            
                        }
                        
                        if (somiconchk==1 && somchanged==false){
                        somchanged = true;
                        Main.volumeicon.setCreepTipo(0);  
                        musica.volumeMute();
                        musica2.volumeMute();
                      
                         
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
         
             //PAUSE
         if (intro.timeEnded()){
                if (Main.mouse.isOverObject(Main.pauseicon)){
                Main.pauseicon.setCurrFrame(0);
                Main.pauseicon.draw();
                if (MouseCheck ==1&& PauseTime.timeEnded()){
                    MouseCheck =0;
                    int pausetipo = Main.pauseicon.getTipoCreep();
                    Main.pauseicon = new Sprite(Main.pause[Main.pauseicon.getTipoCreep()],2);   
                 if (pausetipo==0 && pausechanged==false){
                        pausechanged = true;
                        Main.pauseicon.setCreepTipo(1);
                        }   
                        if (pausetipo==1 && pausechanged==false){
                        pausechanged = true;
                        Main.pauseicon.setCreepTipo(0);  
                        }   
                    Main.pauseicon.x =12;
                    Main.pauseicon.y =648;
                     
                    pausechanged = false; 
                    musica2.stop();
                    musica.stop();
                    Main.pauseicon.setCurrFrame(0);
            Main.background.draw();
            Main.volumeicon.draw();
            Main.pauseicon.draw();
            levelind2.draw();
            
            if(lvl==9){
                bossvida_label.draw();
            for (int z=0;z<bosslife;z++){
                   minipedo[z].draw();
            }
            }
            for (int v=0;v<vidas;v++){
                lifes[v].draw();
            }
            
            
           Main.janela.drawText(Integer.toString(score), 15, 210, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
           pause.draw();
           Main.pauseicon.setCurrFrame(1);
           Main.pauseicon.draw();
           Main.janela.update();
                    
                    execpause=true;
                    
                     PauseTime = new Time(0,0,1,0,0,false);   
                     Pausado();
                     musica2.play();
                
                }
                }
                else {
                    Main.pauseicon.setCurrFrame(1);
                }
                
            }
         
   //DESENHA O MENU DE FORA POR CIMA        
            Main.background.draw();
            Main.volumeicon.draw();
            Main.pauseicon.draw();
            levelind2.draw();
            
            if(lvl==9){
                bossvida_label.draw();
            for (int z=0;z<bosslife;z++){
                   minipedo[z].draw();
            }
            }
            for (int v=0;v<vidas;v++){
                lifes[v].draw();
            }
            
            
           Main.janela.drawText(Integer.toString(score), 15, 210, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
    
    //DESENHA ANIMAÇÃO 'POW'        
         if (powcheck == true){
             
             pow.update();
             pow.draw();
          if(pow.isPlaying()== false){
               pow.hide();
           }
         }
    //DESENHA ANIMAÇÃO BARRA ESPECIAL     
         if (barraespecialcheck == true){
             
             barraespecial.update();
             barraespecial.draw();
          if(barraespecialtime.timeEnded()){
               barraespecial.hide();
           }
         }
      
       
            Main.janela.update(); 
            Main.janela.delay(25);
                
        //GANHOU RODADA NORMAL
            if (killed==Main.qtdinimigoslevel[lvl] && lvl != 9){
                exec=false;
                musica2.stop();
                
                manolo.draw(); 
                win.play();
                Main.pauseicon.setCurrFrame(1);
                Main.volumeicon.setCurrFrame(1);
                Main.pauseicon.draw();
                Main.volumeicon.draw();
                
                Main.janela.drawText(Integer.toString(score), 15, 210, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
                Main.janela.update(); 
                Main.janela.delay(1000);
                lvl++;
                killed=0;
                while(win.isExecuting()){
                    System.out.println(win.isExecuting());
                }
                new Level();
               
                
            }
 
}
    
}

public static void inimigos(int tipocreep) throws InterruptedException {
    int i = tipocreep;   

    //GERA POSIÇÃO ALEATORIA
    
     Ly[0] = (float) (Main.espaco_y1 + (Math.random()*(521-creep_tipo[i].height)));
     Ly[1] = Main.espaco_y1 - creep_tipo[i].height;
     Ly[2] = (float) (Main.espaco_y1 + (Math.random()*(521-creep_tipo[i].height)));
     Ly[3] = 700 - Main.espaco_y2 + creep_tipo[i].height;
     Lx[0] = Main.espaco_x1 - creep_tipo[i].width;
     Lx[1] = (float) (Main.espaco_x1 + (Math.random()*(524 - creep_tipo[i].width)));
     Lx[2] = 750 - Main.espaco_x2 + creep_tipo[i].width;
     Lx[3] = (float) (Main.espaco_x1 + (Math.random()*(524 - creep_tipo[i].width)));
            }

public static void CriarCreep(int i,int h,int tipocreep) {
                        inimigo[i] = new Sprite(Main.creep[tipocreep],2);
                        inimigo[i].setCreepTipo(tipocreep);
                        if (h==0||h==1)
                        inimigo[i].setCurrFrame(0);
                        if (h==2||h==3)
                        inimigo[i].setCurrFrame(1);
                        inimigo[i].setCreepLado(h);
                        inimigo[i].x = Lx[h];
                        inimigo[i].y = Ly[h];
}

 public static void Pausado(){
     
     
      //PAUSE
     while (execpause){
         if(Main.mouse.isLeftButtonPressed())
               MouseCheck=1;
           else
               MouseCheck=0;
                if (Main.mouse.isOverObject(Main.pauseicon)){
                       Main.pauseicon.setCurrFrame(0);
                if (MouseCheck ==1 && PauseTime.timeEnded()){
                    MouseCheck =0;
                    int pausetipo = Main.pauseicon.getTipoCreep();
                    Main.pauseicon = new Sprite(Main.pause[Main.pauseicon.getTipoCreep()],2);   
                 if (pausetipo==0 && pausechanged==false){
                        pausechanged = true;
                        Main.pauseicon.setCreepTipo(1);
                        }   
                        if (pausetipo==1 && pausechanged==false){
                        pausechanged = true;
                        Main.pauseicon.setCreepTipo(0);  
                        }   
                    Main.pauseicon.x =12;
                    Main.pauseicon.y =648;
                     
                    pausechanged = false; 
                    
                    Main.pauseicon.setCurrFrame(0);
                    pause.draw();
                    Main.pauseicon.draw();
                    Main.janela.update();
                    execpause=false;
                    PauseTime = new Time(0,0,1,0,0,false);
                }
                }
                else {
                    Main.pauseicon.setCurrFrame(1);
                }
                
                
            Main.background.draw();
            Main.volumeicon.draw();
            Main.pauseicon.draw();
            levelind2.draw();
            
            if(lvl==9){
                bossvida_label.draw();
            for (int z=0;z<bosslife;z++){
                   minipedo[z].draw();
            }
            }
            for (int v=0;v<vidas;v++){
                lifes[v].draw();
            }
           Main.janela.drawText(Integer.toString(score), 15, 210, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
           pause.draw();
                     pause.draw();
                     Main.pauseicon.draw();
                     Main.janela.update(); 
                     Main.janela.delay(25);
 }
 }
 
 public static void LimpaVetorInimigos(){
     for (int l=0;l<300;l++){
         inimigo[l]=null;
     }
 }
 
}
      
       


