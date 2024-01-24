import java.io.*;

enum TokenType{ NUM,SOMA, MULT,SUB,DIV,APar,FPar, EOF}

class Token{
	//Aqui alteramos para que o construtor aceite strings
  String lexema;
  TokenType token;

 	Token (String l, TokenType t)
	{
		lexema=l;
		token = t;
	}
	Token (char l, TokenType t)
	{
		//Se for char é convertido para String
		lexema=String.valueOf(l);
		token = t;
	}

}

class AnaliseLexica {
	//alterando para poder devolver o caractere lido
	PushbackReader arquivo;

	AnaliseLexica(String a) throws Exception {

		this.arquivo = new PushbackReader(new FileReader(a));

	}

	Token getNextToken() throws Exception {
		// Token token;
		int eof = -1;
		char currchar;
		int currchar1;

		do {
			currchar1 = arquivo.read();
			currchar = (char) currchar1;
		} while (currchar == '\n' || currchar == ' ' || currchar == '\t' || currchar == '\r');

		//Verifica se chegou ao fim do arquivo ou se é uma quebra de linha
		if (currchar1 != eof && currchar1 != 10)
		{
			//Verifica se é um digito
			if (currchar >= '0' && currchar <= '9') {
				StringBuilder lexemaString = new StringBuilder(); //No Stringbuilder vai ficar armazenado o lexema
				lexemaString.append(currchar); //adiciona o primeiro caractere ao lexema
				currchar1 = arquivo.read(); //Lê o próximo caractere
				currchar = (char) currchar1; //Casting para converter para char

				//Enquanto for um digito ele repete o processo anterior
				while (currchar >= '0' && currchar <= '9') {
					lexemaString.append(currchar);
					currchar1 = arquivo.read();
					currchar = (char) currchar1;
				}

				//Se o caractere lido não for um digito ele será devolvido para o arquivo
				arquivo.unread(currchar); //Devolve o último caractere que foi lido

				return (new Token(lexemaString.toString(), TokenType.NUM)); //Retorna o token NUM
			}
			else
				switch (currchar){
					case '(':
						return (new Token (currchar,TokenType.APar));
					case ')':
						return (new Token (currchar,TokenType.FPar));
					case '+':
						return (new Token (currchar,TokenType.SOMA));
					case '*':
						return (new Token (currchar,TokenType.MULT));
					case '-':
						return (new Token (currchar,TokenType.SUB));
					case '/':
						return (new Token (currchar,TokenType.DIV));

					default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
				}
		}

		arquivo.close();

		return (new Token(currchar,TokenType.EOF));

	}
}