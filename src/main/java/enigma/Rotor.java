package enigma;

public class Rotor {

    //attributs de la classe
    private int position;
    //alphabet d'entrée
    private int[] cipher = new int[26];
    //alphabet de sortie
    private int[] bcipher = new int[26];
    private int notch1 = -1;
    private int notch2 = -1;

    //assesseurs
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int posn) {
        position = posn;
    }
    //vient créer le rotor (avec le constructeur correspondant) en fonction de la longeur de la cahine notches
	public static Rotor rotorFactory(String str, String notches){
		char[] s = str.trim().replace(" ", "").toCharArray();
		int[] cipher = new int[26];
		for (int i = 0; i< 26; i++){
			cipher[i] = toIndex(s[i]);
		}
		s = notches.trim().replace(" and ", "").toCharArray();
		if (s.length == 2){
			return new Rotor(cipher, toIndex(s[0]), toIndex(s[1]));
		} else {
			return new Rotor(cipher, toIndex(s[0]));
		}
		
	}
	//constructeurs à 2 et 3 paramètres
	Rotor(int[] c, int notch1, int notch2) {
		this.notch1 = notch1;
		this.notch2 = notch2;
		cipher = c;
		createBCipher();
	}
	
	Rotor(int[] c, int notch1) {
		this.notch1 = notch1;
		cipher = c;
		createBCipher();
	}
        //convertie la position de cipher à partir de p
    public int convertForward(int p) {
        return ((cipher[((p+position)%26+26)%26]-position)%26+26)%26;
    }
    //convertie la position de bcipher à partie de e
    public int convertBackward(int e) {
        return ((bcipher[((e+position)%26+26)%26]-position)%26+26)%26;
    }
    //fait avancer la position d'une position
    public void advance() {
        position = (position+1) % 26;
    }
    
    protected boolean atNotch() {
        return (position == notch1 || position == notch2);
    }

    
    protected static char toLetter(int p) {
        return (char)(p + 'A');
    }
  
    protected static int toIndex(char c) {
        return c - 'A';
    }
    
    private void createBCipher() {
            for(int i =0; i<26; i++)
                    bcipher[cipher[i]] = i;
    }



}
