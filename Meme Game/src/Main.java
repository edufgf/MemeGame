

import jplay.*;

public class Main {
    
static Window janela;
static Keyboard teclado;
static Mouse mouse;
static GameImage background;
static String[] cenario,levelind,especiais_string,musicintro,musicloop,creep,som,pause;
static Sprite[] especiais;
static Sprite volumeicon,pauseicon;
static int[] intromusictime,especialduration,especialrespawn,inimigorespawn,qtdinimigoslevel,valor;
static float espaco_x1,espaco_x2,espaco_y1,espaco_y2;
static float[] vel;
static boolean bonus;
  
    public static void main(String[] args) throws InterruptedException {
        
        janela = new Window(750,700);
        teclado = janela.getKeyboard();
        mouse = janela.getMouse();
        background = new GameImage("backinv3.png");
        volumeicon = new Sprite("SomResume.png",2);
        volumeicon.setCreepTipo(1);
        volumeicon.setCurrFrame(1);
        pauseicon = new Sprite("PauseButton.png",2);
        pauseicon.setCreepTipo(1);
        pauseicon.setCurrFrame(1);
        bonus =false;
        cenario = new String[201];
        levelind = new String[201];
        especiais_string = new String[7];
        musicintro = new String[201];
        musicloop = new String[201];
        creep = new String[10];
        som = new String[2];
        pause = new String[2];
        especiais = new Sprite[7];
        intromusictime = new int[201];
        especialduration = new int[7];
        especialrespawn = new int[201];
        inimigorespawn = new int[201];
        qtdinimigoslevel = new int[201];
        valor = new int[2];
        vel = new float[2];
        espaco_x1=166;
        espaco_x2=60;
        espaco_y1=104;
        espaco_y2=75;         
                
        //definir cenarios
        cenario[0]= "cenario1.png";
        cenario[1]= "cenario2.png";
        cenario[2]= "cenario3.png";
        cenario[3]= "cenario4.png";
        cenario[4]= "cenario5.png";
        cenario[5]= "cenario6.png";
        cenario[6]= "cenario7.png";
        cenario[7]= "cenario8.png";
        cenario[8]= "cenario9.png";
        cenario[9]= "cenarioboss.png";
        cenario[10]= "cenario1.png";
        cenario[11]= "cenario1.png";
        
        //definir musicintro delay
       intromusictime[0]=17;
       intromusictime[1]=17;
       intromusictime[2]=17;
       intromusictime[3]=17;
       intromusictime[4]=17;
       intromusictime[5]=17;
       intromusictime[6]=26;
       intromusictime[7]=17;
       intromusictime[8]=17;     
       intromusictime[9]=21;
       intromusictime[10]=16;
       intromusictime[11]=117;
       
       // definir level
       levelind[0]= "level1.png";
       levelind[1]= "level2.png";
       levelind[2]= "level3.png";
       levelind[3]= "level4.png";
       levelind[4]= "level5.png";
       levelind[5]= "level6.png"; 
       levelind[6]= "level7.png"; 
       levelind[7]= "level8.png"; 
       levelind[8]= "level9.png"; 
       levelind[9]= "levelboss.png";
       levelind[10]= "levelbonus.png";
       levelind[11]= "level1.png";
       
       //definir manolos
       especiais_string[1]= "fu1.png";
       especiais_string[2]= "troll1.png";
       especiais_string[3]= "pokerface.png";
       especiais_string[4]= "foreveralone.png";
       especiais_string[5]= "sugador.png";
       especiais_string[6]= "fuckyeah.png";
       //definir tipos de creep
       creep[0]= "pedo.png";
       creep[1]= "pedo2.png";
       //definir os sons
       som[0]="SomResume.png";
       som[1]="SomMute.png";
       //definir pause/play
       pause[0]="PauseButton.png";
       pause[1]="PlayButton.png";
       //defini valor dos creeps(score)
       valor[0]=10;
       valor[1]=15;
       //defini velocidade dos creeps por tipo
       vel[0]=2.0f;
       vel[1]=2.5f;
       //definir duração
       especialduration[1]= 8;
       especialduration[2]= 7;
       especialduration[3]= 7;
       especialduration[4]= 10;
       especialduration[5]= 5;
       especialduration[6]= 4;
       //definir especialrespawn
       especialrespawn[0]= 5;
       especialrespawn[1]= 5;
       especialrespawn[2]= 5;
       especialrespawn[3]= 5;
       especialrespawn[4]= 5;
       especialrespawn[5]= 5;
       especialrespawn[6]= 5;
       especialrespawn[7]= 5;
       especialrespawn[8]= 5;
       especialrespawn[9]= 5;
       especialrespawn[10]= 99999999;
       especialrespawn[11]= 5;
       //definir inimigos respawn time
       inimigorespawn[0] = 6;
       inimigorespawn[1] = 4;
       inimigorespawn[2] = 6;
       inimigorespawn[3] = 4;
       inimigorespawn[4] = 6;
       inimigorespawn[5] = 6;
       inimigorespawn[6] = 4;
       inimigorespawn[7] = 4;
       inimigorespawn[8] = 4;
       inimigorespawn[9] = 4;
       inimigorespawn[10] = 6;
       inimigorespawn[11] = 6;
       
       
       //definir musicintro
       musicintro[0] = "musicintro01.wav";
       musicintro[1] = "musicintro02.wav";
       musicintro[2] = "musicintro03.wav";
       musicintro[3] = "musicintro04.wav";
       musicintro[4] = "musicintro05.wav";
       musicintro[5] = "musicintro06.wav";
       musicintro[6] = "musicintro07.wav";
       musicintro[7] = "musicintro08.wav";
       musicintro[8] = "musicintro09.wav";
       musicintro[9] = "musicintro10.wav";
       musicintro[10] = "musicintrobonus.wav";
       musicintro[11] = "musicintro01.wav";
       
       //definir musicloop
       musicloop[0] = "musicloop01.wav";
       musicloop[1] = "musicloop02.wav"; 
       musicloop[2] = "musicloop03.wav"; 
       musicloop[3] = "musicloop04.wav"; 
       musicloop[4] = "musicloop05.wav"; 
       musicloop[5] = "musicloop06.wav"; 
       musicloop[6] = "musicloop07.wav";
       musicloop[7] = "musicloop08.wav"; 
       musicloop[8] = "musicloop09.wav";
       musicloop[9] = "musicloop10.wav";
       musicloop[10] = "musicloopbonus.wav";
       musicloop[11] = "musicloop01.wav";
       
       //definir qtd de inimigos no level
       qtdinimigoslevel[0]=12;
       qtdinimigoslevel[1]=12;
       qtdinimigoslevel[2]=24;
       qtdinimigoslevel[3]=24;
       qtdinimigoslevel[4]=12;
       qtdinimigoslevel[5]=24;
       qtdinimigoslevel[6]=36;
       qtdinimigoslevel[7]=36;
       qtdinimigoslevel[8]=48;    
       qtdinimigoslevel[9]=90;
       qtdinimigoslevel[10]=246;
       qtdinimigoslevel[11]=90;
       

       
       new Menu();
       
      
        
    }
 
}
