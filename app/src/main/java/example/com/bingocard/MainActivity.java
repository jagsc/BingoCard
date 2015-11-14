package example.com.bingocard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Button[][] btn = new Button[5][5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                String btnidstr= "btn_"+Integer.toString(i)+"_"+Integer.toString(j);
                int viewId = getResources().getIdentifier(btnidstr,"id",getPackageName());
                btn[i][j] = (Button)findViewById(viewId);
                btn[i][j].setOnClickListener(this);
            }
        }
    }


        public void onClick(View v){
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    String btnidstr = "btn_"+Integer.toString(i)+"_"+Integer.toString(j);
                    int viewId = getResources().getIdentifier(btnidstr,"id",getPackageName());
                    if(v.getId()==viewId){
                        btn[i][j].setText(Integer.toString(i)+Integer.toString(j));
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
