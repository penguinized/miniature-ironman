package tiktektoe;

import tiktektoe.Feld.Spieler;
import java.util.*;

public class Player {
	static Spieler pl = Spieler.X;
	static Feld[][] field;
	static Feld x, o;
	static int in, h, n, m;
	static boolean host = false, be = false;

	public static void main(String[] args) {
		//TODO: I/O BT
		Scanner sc = new Scanner(System.in);
		
		int i = 0;
		field = new Feld[3][3];
		field[0][0] = new Feld(1);
		field[0][1] = new Feld(2);
		field[0][2] = new Feld(3);
		field[1][0] = new Feld(4);
		field[1][1] = new Feld(5);
		field[1][2] = new Feld(6);
		field[2][0] = new Feld(7);
		field[2][1] = new Feld(8);
		field[2][2] = new Feld(9);

		field[0][0].hor = new Feld[2];
		field[0][0].hor[0] = field[0][1];
		field[0][0].hor[1] = field[0][2];
		field[0][1].hor = new Feld[2];
		field[0][1].hor[0] = field[0][0];
		field[0][1].hor[1] = field[0][2];
		field[0][2].hor = new Feld[2];
		field[0][2].hor[0] = field[0][0];
		field[0][2].hor[1] = field[0][1];
		field[1][0].hor = new Feld[2];
		field[1][0].hor[0] = field[1][1];
		field[1][0].hor[1] = field[1][2];
		field[1][1].hor = new Feld[2];
		field[1][1].hor[0] = field[1][0];
		field[1][1].hor[1] = field[1][2];
		field[1][2].hor = new Feld[2];
		field[1][2].hor[0] = field[1][0];
		field[1][2].hor[1] = field[1][1];
		field[2][0].hor = new Feld[2];
		field[2][0].hor[0] = field[2][1];
		field[2][0].hor[1] = field[2][2];
		field[2][1].hor = new Feld[2];
		field[2][1].hor[0] = field[2][0];
		field[2][1].hor[1] = field[2][2];
		field[2][2].hor = new Feld[2];
		field[2][2].hor[0] = field[2][0];
		field[2][2].hor[1] = field[2][1];
		field[0][0].ver = new Feld[2];
		field[0][0].ver[0] = field[1][0];
		field[0][0].ver[1] = field[2][0];
		field[1][0].ver = new Feld[2];
		field[1][0].ver[0] = field[0][0];
		field[1][0].ver[1] = field[2][0];
		field[2][0].ver = new Feld[2];
		field[2][0].ver[0] = field[0][0];
		field[2][0].ver[1] = field[1][0];
		field[0][1].ver = new Feld[2];
		field[0][1].ver[0] = field[1][1];
		field[0][1].ver[1] = field[2][1];
		field[1][1].ver = new Feld[2];
		field[1][1].ver[0] = field[0][1];
		field[1][1].ver[1] = field[2][1];
		field[2][1].ver = new Feld[2];
		field[2][1].ver[0] = field[0][1];
		field[2][1].ver[1] = field[1][1];
		field[0][2].ver = new Feld[2];
		field[0][2].ver[0] = field[1][2];
		field[0][2].ver[1] = field[2][2];
		field[1][2].ver = new Feld[2];
		field[1][2].ver[0] = field[0][2];
		field[1][2].ver[1] = field[2][2];
		field[2][2].ver = new Feld[2];
		field[2][2].ver[0] = field[0][2];
		field[2][2].ver[1] = field[1][2];
		field[0][0].dial = new Feld[2];
		field[0][0].dial[0] = field[1][1];
		field[0][0].dial[1] = field[2][2];
		field[1][1].dial = new Feld[2];
		field[1][1].dial[0] = field[0][0];
		field[1][1].dial[1] = field[2][2];
		field[2][2].dial = new Feld[2];
		field[2][2].dial[0] = field[0][0];
		field[2][2].dial[1] = field[1][1];
		field[2][0].diar = new Feld[2];
		field[2][0].diar[0] = field[1][1];
		field[2][0].diar[1] = field[0][2];
		field[1][1].diar = new Feld[2];
		field[1][1].diar[0] = field[2][0];
		field[1][1].diar[1] = field[0][2];
		field[0][2].diar = new Feld[2];
		field[0][2].diar[0] = field[2][0];
		field[0][2].diar[1] = field[1][1];

		if (host) {
			field[1][1].spieler = pl;
			x = field[1][1];
			i++;
		}
		boolean zug = false;
		out();
		for (; i < 9; i++) {
			zug = false;
			while (!zug) {
				in = sc.nextInt();// empfangen des gegnerzuges
				if (field[(in - 1) / 3][(in - 1) % 3].spieler == Spieler.leer) {
					o = field[(in - 1) / 3][(in - 1) % 3];
					o.spieler = Spieler.O;
					zug = true;
				} else {
					System.out.println("falschen Zug empfangen");
				}
			}
			out();
			if (o.win()) {
				break;
			}
			i++;
			if (x != null) {
				h = x.reagieren();
			} else {
				h = 0;
			}
			be = false;
			System.out.println(h);
			if (h == 0) {
				be = true;
				h = o.reagieren();
				System.out.println(h);
			} else {
				x = field[(h - 1) / 3][(h - 1) % 3];
				x.spieler = pl;
				out();
				break;
			}
			if (be) {
				if (h == 0) {
					if (field[1][1].spieler == Spieler.leer) {
						x = field[1][1];
						x.spieler = pl;
					} else {
						if (i % 2 == 0) {
							System.out.println(i);
							h = o.nummer;
							n = (h - 1) / 3;
							m = (h - 1) % 3;
							n = (n == 2) ? 0 : 2;
							m = (m == 2) ? 0 : 2;
							System.out.println(n + "," + m);
							if (field[n][m].spieler == Spieler.leer) {
								x = field[n][m];
								x.spieler = pl;
							} else {
								if (i == 8) {
									break;
								} else {
									if (x.nummer == 1 || x.nummer == 3
											|| x.nummer == 7 || x.nummer == 9) {
										if (field[0][0].spieler == Spieler.leer) {
											x = field[0][0];
											x.spieler = pl;
										} else {
											if (field[0][2].spieler == Spieler.leer) {
												x = field[0][2];
												x.spieler = pl;
											} else {
												if (field[2][0].spieler == Spieler.leer) {
													x = field[0][0];
													x.spieler = pl;
												} else {
													if (field[2][2].spieler == Spieler.leer) {
														x = field[2][2];
														x.spieler = pl;
													} else {
														for (h = 0; h < 9; h++) {
															if (field[h / 3][h % 3].spieler == Spieler.leer) {
																x = field[h / 3][h % 3];
																x.spieler = pl;
																break;
															}
														}
													}
												}
											}
										}
									} else {
										for (h = 1; h < 9; h = h + 2) {
											if (field[h / 3][h % 3].spieler == Spieler.leer) {
												x = field[h / 3][h % 3];
												x.spieler = pl;
												break;
											}
										}
									}

								}
							}
						} else {
							if (i == 8) {
								break;
							} else {
								if (o.hor[0].spieler == Spieler.leer
										&& (o.hor[0].nummer == 0 || o.hor[0].nummer == 7)) {
									x = o.hor[0];
									x.spieler = pl;
									out();
									continue;
								}
								if (o.hor[1].spieler == Spieler.leer
										&& (o.hor[1].nummer == 3 || o.hor[1].nummer == 9)) {
									x = o.hor[1];
									x.spieler = pl;
									out();
									continue;
								}
								if (o.ver[0].spieler == Spieler.leer
										&& (o.ver[0].nummer == 0 || o.ver[0].nummer == 7)) {
									x = o.ver[0];
									x.spieler = pl;
									out();
									continue;
								}
								if (o.ver[1].spieler == Spieler.leer
										&& (o.ver[1].nummer == 3 || o.ver[1].nummer == 9)) {
									x = o.ver[1];
									x.spieler = pl;
									out();
									continue;
								}

								if (x.nummer == 1 || x.nummer == 3
										|| x.nummer == 7 || x.nummer == 9) {
									if (field[0][0].spieler == Spieler.leer) {
										x = field[0][0];
										x.spieler = pl;
									} else {
										if (field[0][2].spieler == Spieler.leer) {
											x = field[0][2];
											x.spieler = pl;
										} else {
											if (field[2][0].spieler == Spieler.leer) {
												x = field[0][0];
												x.spieler = pl;
											} else {
												if (field[2][2].spieler == Spieler.leer) {
													x = field[2][2];
													x.spieler = pl;
												} else {
													for (h = 0; h < 9; h++) {
														if (field[h / 3][h % 3].spieler == Spieler.leer) {
															x = field[h / 3][h % 3];
															x.spieler = pl;
															break;
														}
													}
												}
											}
										}
									}
								} else {
									for (h = 1; h < 9; h = h + 2) {
										if (field[h / 3][h % 3].spieler == Spieler.leer) {
											x = field[h / 3][h % 3];
											x.spieler = pl;
											break;
										}
									}
								}

							}

						}
					}
				} else {
					x = field[(h - 1) / 3][(h - 1) % 3];
					x.spieler = pl;
				}

			}
			out();
		}
	}

	public static void out() {
		System.out.println(" " + field[0][0].spieler + "|"
				+ field[0][1].spieler + "|" + field[0][2].spieler + " ");
		System.out.println("--+-+--");
		System.out.println(" " + field[1][0].spieler + "|"
				+ field[1][1].spieler + "|" + field[1][2].spieler + " ");
		System.out.println("--+-+--");
		System.out.println(" " + field[2][0].spieler + "|"
				+ field[2][1].spieler + "|" + field[2][2].spieler + " ");

	}
}
