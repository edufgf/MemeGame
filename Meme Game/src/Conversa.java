
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import jplay.Sprite;
import jplay.*;



public class Conversa {

    public Sprite[] active;
    public String[] fala;
    public static int cont,globalcont,MouseCheck;
    public Sound music;
    
    public Conversa() throws InterruptedException{
        //Definicoes
        boolean somchanged =false;
        Time SomTime = new Time(0,0,1,0,0,false);
        music = new Sound("musicconversa.wav");
        Keyboard teclas = Main.janela.getKeyboard();
        teclas.addKey(KeyEvent.VK_SPACE);
        globalcont++; 
        active = new Sprite[100];
        fala = new String[100]; 
        //CV1
        fala[0] = "Hmm... que garotinha mais saborosa!";
        fala[1] = "Ahn?!";
        fala[2] = "Ei espere, eu não sou uma garotinha.";
        fala[3] = "Vamos pegá-la !";
        fala[4] = "Fuck";
        fala[5] = "Cada inimigo vale 10 pontos.";
        //CV2
        fala[6] = "Então está garotinha está se fazendo de difícil?";
        fala[7] = "Eu já disse";
        fala[8] = "Eu não sou uma garotinha !!";
        fala[9] = "É das difíceis que eu mais gosto";
        fala[10] = "Agora ninguêm me segura";
        fala[11] = "Meu Deus, eu não mereço isso";
        fala[12] = "Cada novo inimigo vale 15 pontos.";
        //CV3-BOSS
        fala[13] = "Então é você,a garotinha de que todos falam";
        fala[14] = "Eu não sou...";
        fala[15] = "uma...";
        fala[16] = "GAROTINHA !!!!";
        fala[17] = "Hum... Está com raivinha é ?";
        fala[18] = "Hahaha";
        fala[19] = "Pois bem";
        fala[20] = "Exército,";
        fala[21] = "Todos atrás da Senhorita Raivosa !!!";
        fala[22] = "urr...";
        fala[23] = "Agora estou realmente encrencado";
        //CV4-BOSS Morreu
        fala[24] = "Eu não posso acreditar";
        fala[25] = "Fui derrotado por uma garotinha";
        fala[26] = "Grr !";
        fala[27] = "Ei espere";
        fala[28] = "?";
        fala[29] = "Vendo de perto,eu vejo que você não tem cabelo de mulher";
        fala[30] = "??";
        fala[31] = "Então você é um cara !";
        fala[32] = "Finalmente !";
        fala[33] = "Sendo assim, é minha obrigação ter que matá-lo";
        fala[34] = "Mas o que ?!";
        fala[35] = "Não agora,pois você me enfraqueceu";
        fala[36] = "Ah bem";
        fala[37] = "Mas me aguarde, ainda vamos nos enfrentar novamente";
        fala[38] = "[Nem fudendo que vou ficar por aqui,vo meter o pé]";
        fala[39] = "E não pense em escapar.Meus colegas já foram avisados sobre você";
        fala[40] = "[Filho da Put* desgraçado !!111!1!!]";
        fala[41] = "Então até nosso próximo encontro.Isso é,se você sobreviver até lá";
        fala[42] = "Beleza tio pedófilo, até mais";
        fala[43] = "Boss derrotado. +1000 Pontos.";
        //CV5-BONUS
        fala[44] = "Rodada Bonus !";
        fala[45] = "Desta vez os inimigos vão andar em linha reta,apenas desvie";
        fala[46] = "Atenção, perder todas as vidas irá terminar a rodada Bonus";
        fala[47] = "As vidas que você tinha continuarão no próximo level";
        fala[48] = "Fique atento para pegar os bonus que aparecerem";
        fala[49] = "Boa Sorte !";
        //CV6-BonusEnd;
        fala[50] = "Você coletou "+Level.bonusget+" bonus.";
        if (Level.bonusget==0){
        fala[51] = "Infelizmente você não coletou nenhum bonus.";
        fala[52] = "Mais sorte no próximo.";
        }
        if ((Level.bonusvida + Level.bonusget) <=5&& Level.bonusget !=0){
        fala[51] = "Seus "+Level.bonusget+" bonus foram convertidos em vidas.";
        fala[52] = "Boa Sorte nos próximos níveis.";
        }else if ((Level.bonusvida + Level.bonusget) > 5&& Level.bonusget !=0){
        fala[51] = "Você alcançou seu limite de vidas.";
        fala[52] = (Level.bonusget-((Level.bonusvida + Level.bonusget)-5)) + " Convertidos em vidas e "+((Level.bonusvida + Level.bonusget)-5)+" convertidos em 300 pontos cada.";
        }
        
         //CV1
        active[0] = new Sprite("CV1pedo1.png");
        active[1] = new Sprite("CV1manolo1.png");
        active[2] = new Sprite("CV1manolo2.png");
        active[3] = new Sprite("CV1pedo1.png");
        active[4] = new Sprite("CV1manolo3.png");
        active[5] = new Sprite("CVinfo.png");
        //CV2
        active[6] = new Sprite("CV1pedo1.png");
        active[7] = new Sprite("CV1manolo2.png");
        active[8] = new Sprite("CV2manolo1.png");
        active[9] = new Sprite("CV1pedo1.png");
        active[10] = new Sprite("CV2pedo1.png");
        active[11] = new Sprite("CV2manolo2.png");
        active[12] = new Sprite("CVinfo.png");
        //CV3-BOSS
        active[13] = new Sprite("CV3pedo1.png");
        active[14] = new Sprite("CV2manolo1.png");
        active[15] = new Sprite("CV3manolo1.png");
        active[16] = new Sprite("CV3manolo2.png");
        active[17] = new Sprite("CV3pedo1.png");
        active[18] = new Sprite("CV3pedo1.png");
        active[19] = new Sprite("CV3pedo1.png");
        active[20] = new Sprite("CV3pedo1.png");
        active[21] = new Sprite("CV3pedo2.png");
        active[22] = new Sprite("CV3manolo3.png");
        active[23] = new Sprite("CV1manolo3.png");
        //CV4-BOSS Morreu
        active[24] = new Sprite("CV3pedo1.png");
        active[25] = new Sprite("CV3pedo1.png");
        active[26] = new Sprite("CV3manolo1.png");
        active[27] = new Sprite("CV3pedo1.png");
        active[28] = new Sprite("CV4manolo1.png");
        active[29] = new Sprite("CV3pedo1.png");
        active[30] = new Sprite("CV4manolo2.png");
        active[31] = new Sprite("CV3pedo1.png");
        active[32] = new Sprite("CV4manolo3.png");
        active[33] = new Sprite("CV3pedo1.png");
        active[34] = new Sprite("CV1manolo2.png");
        active[35] = new Sprite("CV3pedo1.png");
        active[36] = new Sprite("CV4manolo4.png");
        active[37] = new Sprite("CV3pedo1.png");
        active[38] = new Sprite("CV4manolo5.png");
        active[39] = new Sprite("CV3pedo1.png");
        active[40] = new Sprite("CV1manolo2.png");
        active[41] = new Sprite("CV3pedo1.png");
        active[42] = new Sprite("CV1manolo1.png");
        active[43] = new Sprite("CVinfo.png");
        //CV5-BONUS
        active[44] = new Sprite("CVinfo.png");
        active[45] = new Sprite("CVinfo.png");
        active[46] = new Sprite("CVinfo.png");
        active[47] = new Sprite("CVinfo.png");
        active[48] = new Sprite("CVinfo.png");
        active[49] = new Sprite("CVinfo.png");
        //CV6-BonusEnd
        active[50] = new Sprite("CVinfo.png");
        active[51] = new Sprite("CVinfo.png");
        active[52] = new Sprite("CVinfo.png");
        
        
        
        if (Main.volumeicon.getTipoCreep()==0)
         music.volumeMute();
     
        
        
        //Deenha Default
        Level.campo.draw();
        Level.manolo.draw();
        Main.background.draw();
        Main.volumeicon.draw();
        Main.pauseicon.draw();
        Level.levelind2.draw();
            for (int v=0;v<Level.vidas;v++){
                Level.lifes[v].draw();
            }
        Main.janela.drawText(Integer.toString(Level.score), 15, 210, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
        Main.janela.update(); 
        music.play();
        Main.janela.delay(500);
        
        //Começa
        boolean exec = true;
        
        
     while (exec){   
       
       Level.campo.draw();
        Level.manolo.draw();
        Main.background.draw();
        active[cont].x= 175;
        active[cont].y =20;
        active[cont].draw();
        Main.volumeicon.draw();
        Main.pauseicon.draw();
        Level.levelind2.draw();
            for (int v=0;v<Level.vidas;v++){
                Level.lifes[v].draw();
            }
        Main.janela.drawText(Integer.toString(Level.score), 15, 210, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
       Main.janela.drawText(fala[cont], 170+active[cont].width+5, 45, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 16));
       Main.janela.update();
       if (teclas.keyDown(Keyboard.SPACE_KEY)){
           if (cont ==51){
              if ((Level.bonusvida + Level.bonusget) <=5&& Level.bonusget !=0){
              Level.vidas = (Level.bonusvida + Level.bonusget);
              }else if ((Level.bonusvida + Level.bonusget) > 5&& Level.bonusget !=0){
              Level.vidas = 5;
              Level.score += ((Level.bonusvida + Level.bonusget)-5)*300;
        }
           }
           cont++;
           if (cont ==6 || cont ==13 || cont ==24 || cont ==44 || cont ==50 || cont==53) {
               music.stop();
               exec =false;
               new Level();
           }
           
       }
       
        //ANIMAÇÃO DO SOM E MUTE       
       
       
       
            if(Main.mouse.isLeftButtonPressed())
               MouseCheck=1;
           else
               MouseCheck=0;
          
           
            if (Main.mouse.isOverObject(Main.volumeicon)){
                Main.volumeicon.setCurrFrame(0);
                if (MouseCheck==1&&SomTime.timeEnded()){
                        int somiconchk = Main.volumeicon.getTipoCreep();
                        Main.volumeicon = new Sprite(Main.som[Main.volumeicon.getTipoCreep()],2);
                       
                        if (somiconchk==0 && somchanged==false){
                        somchanged = true;
                        Main.volumeicon.setCreepTipo(1);
                        music.volumeResume();                
                        }
                        
                        if (somiconchk==1 && somchanged==false){
                        somchanged = true;
                        Main.volumeicon.setCreepTipo(0);  
                        music.volumeMute();
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
         
       
       
       
       
       
       
       
       
    }
    }
    
}
