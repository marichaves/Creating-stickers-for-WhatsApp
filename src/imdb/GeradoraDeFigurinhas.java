package imdb;
import java.io.File;

import java.io.InputStream;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;

public class GeradoraDeFigurinhas {
	
public void cria(InputStream inputStream, String nomeArquivo ) throws Exception {
    // leitura da imagem original
	//InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
	//InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,12,128,176_AL_.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    	
    // leitura da imagem de fundo
    BufferedImage fundo = ImageIO.read(new File("entrada/base.jpg"));

    // cria nova imagem em memória que combina o tamanho da imagem original com o tamanho da imagem de fundo
    int largura = fundo.getWidth();
    int altura = fundo.getHeight();
  
    BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

    // desenhar a imagem de fundo na nova imagem
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(fundo, 0, 0, null);

    // desenhar a imagem original na nova imagem com uma posição específica
    int x = largura - imagemOriginal.getWidth() - 300 ;
    int y = altura - imagemOriginal.getHeight() - 336;     //exemplo de distância do topo
    int novaLargura = imagemOriginal.getWidth() / 2;
    int novaAltura = imagemOriginal.getHeight() / 2;
    
 // define o tamanho da moldura em pixels
    int moldura = 5;
    graphics.setColor(new Color(40, 33, 24)); // define a cor da moldura como preto
    graphics.fillRect(x - moldura, y - moldura, novaLargura + 2 * moldura, novaAltura + 2 * moldura); // desenha um retângulo preto ao redor da imagem

    

    graphics.drawImage(imagemOriginal, x, y, novaLargura, novaAltura, null);


    // salvar a nova imagem em um arquivo
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));
}


}
