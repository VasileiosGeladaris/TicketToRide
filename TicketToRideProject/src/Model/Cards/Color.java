package Model.Cards;

/**
 * 
 * @author csd3926
 */
public enum Color{
	Blue(132), Purple(254), White(315), Green(193), Red(10), Orange(437), Black(71), Yellow(376), Locomotive(0);
	
	private int pos;

	Color(int pos){
		this.pos = pos;
	}
	
	public int getPos(){
		return this.pos;
	}
}
