public class test {
    private static int friendsPairing(int n,String psf,boolean[] visited)
    {
        int idx=1;
        while(idx<=n)
        {
            if(visited[idx]==false)
            {
                break;
            }
            idx++;
        }
        if(idx>n)
        {
            System.out.println(psf);
            return 1;
        }
        int count=0;
        //single call
        visited[idx]=true;
        count+=friendsPairing(n,psf+idx+" ",visited);
        for (int i = idx+1; i <= n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                count += friendsPairing(n, psf + idx + "" + i + " ", visited);
                visited[i] = false;
            }
        }
        visited[idx]=false;
        return count;
    }
    public static void main(String[] args) {
        boolean[] visited=new boolean["ABC".length()+1];
        friendsPairing(3,"",visited);
    }
}
