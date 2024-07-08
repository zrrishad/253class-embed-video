package com.example.a253classvideoembed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ProgressBar edProg;
    Button bButton;
    ImageView edImag;
    TextView edTitle,eddes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        edProg=findViewById(R.id.edProg);
        bButton=findViewById(R.id.bButton);
        edImag=findViewById(R.id.edimag);
        edTitle=findViewById(R.id.edTitle);
        eddes=findViewById(R.id.eddes);


        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edProg.setVisibility(View.VISIBLE);


                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://ris71.000webhostapp.com/asma/embed.json";

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                edProg.setVisibility(View.GONE);
                                Log.d("serverRes",response);
                                try {
                                    JSONObject jsonObject=new JSONObject(response);

                                    String title=jsonObject.getString("title");
                                    String description=jsonObject.getString("description");
                                    String url=jsonObject.getString("url");
                                    String video_url="https://img.youtube.com/vi/"+url+"/0.jpg";

eddes.setText(description);
edTitle.setText(title);
                                    Picasso.get().load(video_url)
                                            .into(edImag);



                                    edImag.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            MainActivity2.video_url=url;
                                            Intent myIntent=new Intent(MainActivity.this,MainActivity2.class);
                                            startActivity(myIntent);
                                        }
                                    });



                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        edProg.setVisibility(View.GONE);

                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);


            }
        });






    }
}