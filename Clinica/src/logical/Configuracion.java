package logical;

import java.io.Serializable;

public class Configuracion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int maxCitasSist;
	private int maxDoctoresSist;
	private int sano;
	private int regular;
	private int grave;
	private int critico;

}
