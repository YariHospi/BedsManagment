package hcjsm.softech.yari.bedsmanagment.beds;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import hcjsm.softech.yari.bedsmanagment.R;

public class BedsActivity extends AppCompatActivity {
    
    private Toolbar     mToolbar;
    private Fragment    mBedsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beds);
        
        // UI References
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBedsFragment = getSupportFragmentManager().findFragmentById(R.id.beds_container);
        
        
        //Setup
        setUpToolbar();
        setUpBedsFragment();

    }

    private void setUpBedsFragment() {
        if(mBedsFragment == null){
            mBedsFragment = BedsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.beds_container, mBedsFragment)
                    .commit();
        }
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beds, menu);
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
