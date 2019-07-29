package com.sss.vts.Admin.Org_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sss.vts.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.sss.vts.Helper.utils.baseURL_OrgInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class RulesFragment extends Fragment
{
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View v = getView();
        View RootView = inflater.inflate(R.layout.fragment_rules, container, false);
        assert v != null;
        listView = (ListView)RootView.findViewById(R.id.listViewRules);
        getJSON();

        return  RootView;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }
    private void getJSON()
    {
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String>
        {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override

            protected void onPreExecute()
            {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s)
            {
                if (s!=null) {
                    super.onPostExecute(s);
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    try {
                        loadIntoListView(s);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids)
            {

                try {
                    //creating a URL
                    URL url = new URL(baseURL_OrgInfo);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json).append("\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e)
                {
                    return null;
                }

            }
        }
        //creating asyncTask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void loadIntoListView(String json) throws JSONException
    {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listView
        String[] orginfo = new String[jsonArray.length()];

        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++)
        {

            //getting json object from the json array
            JSONObject intro = jsonArray.getJSONObject(i);

            //getting the name from the json object and putting it inside string array
            orginfo[i] = intro.getString("rules");
        }

        //the array adapter to load data into list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, orginfo);

        //attaching adapter to listView
        listView.setAdapter(arrayAdapter);
    }

}
