package net.other.outer;

public class Parcel6 {
	private void internalTracking(boolean b) {
		if (b) {
			class TrackingSlip {
				private String id;

				TrackingSlip(String s) {
					id = s;
				}

				String getSlip() {
					return id;
				}
			}
			TrackingSlip ts = new TrackingSlip("chenssy");
			String string = ts.getSlip();
			System.out.println(string);
		}
	}

	public void track() {
		internalTracking(true);
	}

	public static void main(String[] args) {
		Parcel6 parcel6 = new Parcel6();
		parcel6.track();
	}
}
