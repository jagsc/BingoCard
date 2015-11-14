package example.com.bingocard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Boolean[][] celltrueisAlive = new Boolean[5][5];
    Button[][] btn = new Button[5][5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<5;i++) {
            Random rnd = new Random();
            int rndnum[] = {0,0,0,0,0};
            for(int k=0;k<5;) {
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
                            btn[i][j].setText(Integer.toString(i) + Integer.toString(j));
                            celltrueisAlive[i][j] = false;
                        }
                        break;
                    }
                }
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
