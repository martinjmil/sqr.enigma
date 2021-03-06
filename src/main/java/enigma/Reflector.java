package enigma;

/** Class that represents a reflector in the enigma.
 *  @author
 */
public class Reflector extends Rotor {
	
    //attibuts de la classe
	int[] reflection;
	
//appel le constructeur apr�s initlialis� cipher et len tableau de caract�re s
	public static Reflector reflectorFactory(String str){
		char[] s = str.trim().replace(" ", "").toCharArray();
		int[] cipher = new int[26];
		for (int i = 0; i< 26; i++){
			cipher[i] = toIndex(s[i]);
		}
		return new Reflector(cipher);
	}
//constructeur de la classe 	
	Reflector(int[] r){
		super(r,0);
		reflection = r;
	}
        
    public int convertForward(int p) {
        return ((reflection[((p)%26+26)%26])%26+26)%26;
    }
//surcharge de la m�thode "convertBackward"
    @Override
    public int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }
//surcharge de la m�thode "advance"
    @Override
    public void advance() {
    }

}
