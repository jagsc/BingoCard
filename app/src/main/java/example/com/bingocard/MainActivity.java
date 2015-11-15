package example.com.bingocard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Boolean[][] celltrueisAlive = new Boolean[5][5];

    Boolean[] cellcolumntrueisreach = new Boolean[5];
    Boolean[] cellrowtrueisreach = new Boolean[5];
    Boolean[]cellcolumntrueisbingo = new Boolean[5];
    Boolean[]cellrowtrueisbingo = new Boolean[5];
    Boolean[]cellcrosstrueisreach = new Boolean[2];
    Boolean[]cellcrosstrueisbingo = new Boolean[2];
    Button[][] btn = new Button[5][5];

   // TextView resultTxtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //resultTxtview = (TextView)findViewById(R.id.textView);

        cellcrosstrueisreach[0]=false;
        cellcrosstrueisbingo[0]=false;
        cellcrosstrueisreach[1]=false;
        cellcrosstrueisbingo[1]=false;
        for(int i=0;i<5;i++) {
            cellcolumntrueisreach[i]=false;
            cellrowtrueisreach[i]=false;
            cellcolumntrueisbingo[i]=false;
            cellrowtrueisbingo[i]=false;
            int rndnum[] = {0,0,0,0,0};
            for(int k=0;k<5;) {
                long seed = System.currentTimeMillis();
                Random rnd = new Random(seed);
                int temprnd=rnd.nextInt(15)+i*15+1;
                boolean trueisok = true;
                    for(int l=0;l<5;l++) {
                        if (rndnum[l] == temprnd) {
                            trueisok = false;
                            break;
                        }
                    }
                if(trueisok==true){
                    rndnum[k]=temprnd;
                    k++;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int j=0;j<5;j++) {
                String btnidstr= "btn_"+Integer.toString(i)+"_"+Integer.toString(j);
                int viewId = getResources().getIdentifier(btnidstr,"id",getPackageName());
                btn[i][j] = (Button)findViewById(viewId);
                btn[i][j].setOnClickListener(this);
                celltrueisAlive[i][j]=true;
                celltrueisAlive[2][2]=false;
                btn[i][j].setText(Integer.toString(rndnum[j]));
            }
        }
        btn[2][2].setText("Free");
    }


        public void onClick(View v){
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    String btnidstr = "btn_"+Integer.toString(i)+"_"+Integer.toString(j);
                    int viewId = getResources().getIdentifier(btnidstr,"id",getPackageName());
                    if( v.getId()==viewId){
                        if(celltrueisAlive[i][j]==true) {
                            btn[i][j].setText("OK");
                            celltrueisAlive[i][j] = false;
                            judge_cellstate(i,j);
                        }
                        break;
                    }
                }
            }
        }

    public void judge_cellstate(int x,int y){
        int reachcount =0;
        int bingocount =0;
        int cellAlivecount=0;
        for(int i=0;i<5;i++){
            if(celltrueisAlive[i][y]==false){
                cellAlivecount++;
            }
        }
        if(cellAlivecount==4){
            if(cellrowtrueisreach[y]==false) {
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(y);
                reachcount++;
                cellrowtrueisreach[y] = true;
            }
        }
        if(cellAlivecount==5){
            if(cellrowtrueisbingo[y]==false){
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(y);
                bingocount++;
                cellrowtrueisbingo[y] = true;
            }
        }

        cellAlivecount=0;
        for(int i=0;i<5;i++){
            if(celltrueisAlive[4-i][i]==false){
                cellAlivecount++;
            }
        }
        if(cellAlivecount==4){
            if(cellcrosstrueisreach[1]==false){
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(1);
                reachcount++;
                cellcrosstrueisreach[1]=true;
            }
        }
        if(cellAlivecount==5){
            if(cellcrosstrueisbingo[1]==false){
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(1);
                bingocount++;
                cellcrosstrueisbingo[1]=true;
            }
        }


        cellAlivecount=0;
        for(int i=0;i<5;i++){
            if(celltrueisAlive[i][i]==false){
                cellAlivecount++;
            }
        }
        if(cellAlivecount==4){
            if(cellcrosstrueisreach[0]==false){
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(0);
                reachcount++;
                cellcrosstrueisreach[0]=true;
            }
        }
        if(cellAlivecount==5){
            if(cellcrosstrueisbingo[0]==false){
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(0);
                bingocount++;
                cellcrosstrueisbingo[0]=true;
            }
        }

        cellAlivecount=0;
        for(int j=0;j<5;j++){
            if(celltrueisAlive[x][j]==false){
                cellAlivecount++;
            }
        }
        if(cellAlivecount==4){
            if(cellcolumntrueisreach[x]==false){
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(x);
                reachcount++;
                cellcolumntrueisreach[x]=true;
            }
        }
        if(cellAlivecount==5){
            if(cellcolumntrueisbingo[x]==false){
                /*ここにアニメーションを挿入*/
                String tststr = Integer.toString(x);
                bingocount++;
                cellrowtrueisbingo[x] = true;
            }
        }
        if(reachcount>0){
           // resultTxtview.setText("reach:"+Integer.toString(reachcount));
            /*ここにリーチの表示(オーバーレイとか)*/
        }
        if(bingocount>0){
            //resultTxtview.setText("\r\nbingo:"+Integer.toString(bingocount));
            /*ここにビンゴの表示(オーバーレイとか)*/
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
