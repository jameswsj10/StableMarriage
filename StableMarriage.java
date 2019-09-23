import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList; 
import java.util.ArrayList;


public class StableMarriage {
	// number of stable pairings produced

	static void Solution(int N, HashMap<String, ArrayList<String>> females, HashMap<String, Queue<String>> males) {
		HashMap<String, String> stablePairing = new HashMap<>();
		while (stablePairCounts(stablePairing) != N) {
			for (String male : males.keySet()) {
				Queue<String> preflist = males.get(male);
				String female = preflist.peek();

				//we check if the female already has a pair 
				if (!stablePairing.containsKey(female)) {
					stablePairing.put(female, male);
				} else {
					String currFemalePair = stablePairing.get(female);
					// if the male who proposes is a better match than female's current partner
					// , she will leave him on the string and reject current partner
					if (females.get(female).indexOf(currFemalePair) > females.get(female).indexOf(male)) {
						stablePairing.remove(female);
						stablePairing.put(female, male);
						// the current partner crosses off female off his list
						males.get(currFemalePair).remove();
					} else {
						// in the case the proposed man is not better than curr partner, he crosses female off his list
						preflist.remove();
					}
				}
			}
		}
		// print the results
		for (String female : stablePairing.keySet()) {
			System.out.println("{" + female + ", " + stablePairing.get(female) + "}");
		}
	}

	private static int stablePairCounts(HashMap<String, String> stablePairing) {
		if (stablePairing.isEmpty()) {
			return -1;
		}
		return stablePairing.keySet().size();
	}

	public static void main(String[] args) {
		// prompt user for inputs for preference list
		System.out.print("How many females are there in the algorithm? ");
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		// we iterate through the number of females to create hashmap with females and their prefernce list
		HashMap<String, ArrayList<String>> females = new HashMap<>();
		for (int j = 0; j < N; j++) {
			Scanner sc1 = new Scanner(System.in);
			System.out.print("Type in names of a female and her preference list in decreasing order, separated by commas ");
			String femaleNamePref = sc1.nextLine();

			String[] names = femaleNamePref.split(", ");
			ArrayList<String> preferencelist = new ArrayList<>();
			for (int i = 1; i < names.length; i++) {
				preferencelist.add(names[i]);
			}
			females.put(names[0], preferencelist);
		}

		// we iterate through th enumber of males to create hashmaps with males and their pref list
		HashMap<String, Queue<String>> males = new HashMap<>();
		for (int j = 0; j < N; j++) {
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Type in name of a male and his preference list in decreasing order, separated by commas ");
			String maleNamePref = sc2.nextLine();

			String[] names = maleNamePref.split(", ");
			Queue<String> preferencelist = new LinkedList<>();
			for (int i = 1; i < names.length; i++) {
				preferencelist.add(names[i]);
			}
			males.put(names[0], preferencelist);
		}

		// call on solution to print out stable pairings based on stable marriage algorithm
		Solution(N, females, males);

	}
}