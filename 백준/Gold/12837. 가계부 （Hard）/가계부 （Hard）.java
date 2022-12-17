import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by ibyeongmu on 2017. 3. 1..
 */
public class Main {

    static int N, Q, start;
    static long tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (start = 1; start < N; start *= 2) ;


        tree = new long[2 * start];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int method = Integer.parseInt(st.nextToken());
            long p = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            if (method == 1) {
                //업데이트
                update(p,x);
            } else if (method == 2) {
                // 합
                sb.append(sum((int)p,(int)x,1,1,start)).append("\n");
            }
        }
        System.out.println(sb);
    }
    public static void update(long idx,long val){
        int index = (int) (start+idx-1);
        tree[index]+=val;
        while(index>1){
            index/=2;
            tree[index]=tree[index*2]+tree[index*2+1];
        }
    }
    public static long sum(int L,int R,int idx,int temp_L,int temp_R){
        if(temp_L>R||L>temp_R)return 0;
        if(L<=temp_L&&temp_R<=R)return tree[idx];
        int mid = (temp_L+temp_R)/2;
        return sum(L,R,idx*2,temp_L,mid)+sum(L,R,idx*2+1,mid+1,temp_R);
    }


}