
import JPlay.GameImage;
import JPlay.Window;
public class JogoMeu {


    public static void main(String[] args) {
        Window janela = new Window(800,600);
        GameImage backGround = new GameImage("fundo.PNG");
        backGround.setPosition(137,39.5);
        while(true) {
            backGround.draw();
            janela.display();
        }
        
    }

}
