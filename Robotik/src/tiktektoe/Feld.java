package tiktektoe;

public class Feld {
	enum Spieler {
		leer, X, O;

		@Override
		public String toString() {
			if (this.name().equals("leer")) {
				return " ";
			}
			return this.name();
		}
	};

	Spieler spieler;
	int nummer;
	Feld[] hor, ver, diar, dial;

	public Feld(int nummer) {
		this.nummer = nummer;
		this.spieler = Spieler.leer;
	}

	public int reagieren() {
		Feld[] h;
		h = this.dial;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == Spieler.leer)
					|| (h[0].spieler == Spieler.leer && h[1].spieler == this.spieler)) {
				if (h[0].spieler == Spieler.leer) {
					return h[0].nummer;
				} else {
					return h[1].nummer;
				}
			}
		}
		h = this.diar;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == Spieler.leer)
					|| (h[0].spieler == Spieler.leer && h[1].spieler == this.spieler)) {
				if (h[0].spieler == Spieler.leer) {
					return h[0].nummer;
				} else {
					return h[1].nummer;
				}
			}
		}
		h = this.hor;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == Spieler.leer)
					|| (h[0].spieler == Spieler.leer && h[1].spieler == this.spieler)) {
				if (h[0].spieler == Spieler.leer) {
					return h[0].nummer;
				} else {
					return h[1].nummer;
				}
			}
		}
		h = this.ver;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == Spieler.leer)
					|| (h[0].spieler == Spieler.leer && h[1].spieler == this.spieler)) {
				if (h[0].spieler == Spieler.leer) {
					return h[0].nummer;
				} else {
					return h[1].nummer;
				}
			}
		}

		return 0;
	}

	public boolean win() {
		Feld[] h;
		h = this.dial;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == this.spieler)) {
				return true;
			}
		}
		h = this.diar;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == this.spieler)) {
				return true;
			}
		}
		h = this.hor;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == this.spieler)) {
				return true;
			}
		}
		h = this.ver;
		if (h != null) {
			if ((h[0].spieler == this.spieler && h[1].spieler == this.spieler)) {
				return true;
			}
		}

		return false;
	}

}
