	/*
	 * Get all cross-source second degree connection combinations.
	 * 
	 * Assumptions:
	 * 			Each connection combination is output at most once. 
	 * 			For example, A is connected to C via PINTEREST, B is connected to C via FACEBOOK,
	 * 				A is connected to E via PINTEREST, B is connected to E via FACEBOOK,
	 * 				the pair [PINTEREST, FACEBOOK] is shown only once in the output.
	 * 
	 * @param user1 The first user.
	 * @param user2 The second user.
	 * @return The list of connection combinations.
	 */
	static List<Pair> getSecondDegreeConnections(int user1, int user2) {
		List<Pair> pairs = new ArrayList<Pair>();
		for (SocialNetwork n1 : SocialNetwork.values()) {
			for (SocialNetwork n2 : SocialNetwork.values()) {
				if (n2 == n1) {
					continue;
				}
				List<Integer> list1 = getConnections(user1, n1);
				List<Integer> list2 = getConnections(user2, n2);
				list1.retainAll(list2);
				// Check that user1 and user2 have common connected source.
				if (!list1.isEmpty()) {
					pairs.add(new Pair(n1, n2));
				}
			}
		}
		return pairs;
	}
	
// The social network pair.
class Pair {
	SocialNetwork socialNetwork1;
	SocialNetwork socialNetwork2;
	Pair (SocialNetwork s1, SocialNetwork s2) {
		socialNetwork1 = s1;
		socialNetwork2 = s2;
	}
}
