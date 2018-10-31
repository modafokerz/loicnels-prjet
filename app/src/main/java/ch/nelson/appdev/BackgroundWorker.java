package ch.nelson.appdev;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Cette classe a pour but de permettre la connection a la db avec le login et le pwd
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    BackgroundWorker (Context ctx)
    {
        context=ctx;
    }

    /**
     * On envoie l'email et le pwd et cela nous retourne si la connection est ok
     * @param params
     * @return resultat
     */
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_login.php";
        String register_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_register.php";

        mPreferences = context.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        mEditor.putString("flag","0");
        mEditor.commit();

        if(type.equals("login")){
            String loginEmail = params[1];
            String loginPassword = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //send the email and password to the database
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("loginEmail","UTF-8")+"="+URLEncoder.encode(loginEmail,"UTF-8")+"&"
                        +URLEncoder.encode("loginPassword","UTF-8")+"="+URLEncoder.encode(loginPassword,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get response from the database
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dataResponse = "";
                String inputLine = "";
                while((inputLine = bufferedReader.readLine()) != null){
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(dataResponse);

                mEditor.putString("flag","login");
                mEditor.commit();
                return  dataResponse;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("register")){
            String regEmail = params[1];
            String regPassword = params[2];

            try {
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("register_email","UTF-8")+"="+URLEncoder.encode(regEmail,"UTF-8")+"&"
                        +URLEncoder.encode("register_password","UTF-8")+"="+URLEncoder.encode(regPassword,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                mEditor.putString("flag","register");
                mEditor.commit();
                return "Successfully Registered " + regEmail;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    /**
     * Affiche le resultat dans un alertDialog
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {

        String flag = mPreferences.getString("flag","0");

        if(flag.equals("login")){
            String test = "false";
            String password = "";
            String email = "";
            String[] serverResponse = result.split("[,]");
            test = serverResponse[0];
            email = serverResponse[1];
            password = serverResponse[2];

            if(test.equals("true")){
                mEditor.putString("email",email);
                mEditor.commit();
                mEditor.putString("password",password);
                mEditor.commit();
                Intent intent = new Intent(context,NavigationActivity.class);
                context.startActivity(intent);
                //Toast.makeText(context,"Connection réussie",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"Indentifiant incorrect",Toast.LENGTH_LONG).show();
            }
        }
        else if(flag.equals("register")) {
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
        else if (flag.equals("0"))
        {
            Toast.makeText(context,"Erreur flag 0",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
