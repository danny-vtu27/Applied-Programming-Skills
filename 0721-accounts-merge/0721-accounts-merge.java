import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> acc) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();

        for (List<String> a : acc) {
            String name = a.get(0);
            for (int i = 1; i < a.size(); i++) {
                parent.putIfAbsent(a.get(i), a.get(i));
                owner.put(a.get(i), name);
                union(parent, a.get(1), a.get(i));
            }
        }

        Map<String, TreeSet<String>> map = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            map.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for (String root : map.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(owner.get(root));
            list.addAll(map.get(root));
            res.add(list);
        }
        return res;
    }

    String find(Map<String,String> p, String s){
        if(!p.get(s).equals(s)) p.put(s, find(p,p.get(s)));
        return p.get(s);
    }

    void union(Map<String,String> p, String a, String b){
        p.put(find(p,a), find(p,b));
    }
}