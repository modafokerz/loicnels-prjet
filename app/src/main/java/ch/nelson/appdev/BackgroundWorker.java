package ch.nelson.appdev;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


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
import java.util.ArrayList;

/**
 * Cette classe a pour but de permettre la connection a la db avec le login et le pwd
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    private String req;

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
        String registerEscort_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_registerEscort.php";
        String afficheEscort_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_afficheEscort.php";
        String escortInfo_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_infoEscort.php";
        String escortInfoPhoto_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_infoEscortPhoto.php";
        String escortInfoService_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_infoEscortService.php";
        String escortInfoLangue_url = "http://y54uotiox.preview.infomaniak.website/apptchoin/connection_infoEscortLangue.php";

        req = "0";

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


                req = "login";

                return  dataResponse;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register")){
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
                outputStream.close();
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


                req = "register";

                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("afficheEscort")){


            req = "afficheEscort";

            URL url;
            HttpURLConnection urlConnection = null;
            String server_response="";

            try {
                url = new URL(afficheEscort_url);
                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                    //Log.v("CatalogClient", server_response);
                    System.out.println("!!!!!!!!!!!!!! affiche escort !!!!!!!!!!!!!!!!!!!");
                    System.out.println(server_response);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return server_response;

        }
        else if(type.equals("escortInfo")){

            String idEscort = params[1];

            try {
                URL url = new URL(escortInfo_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("idEscort","UTF-8")+"="+URLEncoder.encode(idEscort,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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


                req = "escortInfo";

                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("escortInfoPhoto")){

            String idEscort = params[1];

            try {
                URL url = new URL(escortInfoPhoto_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("idEscort","UTF-8")+"="+URLEncoder.encode(idEscort,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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


                req = "escortInfoPhoto";

                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("escortInfoService")){

            String idEscort = params[1];

            try {
                URL url = new URL(escortInfoService_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("idEscort","UTF-8")+"="+URLEncoder.encode(idEscort,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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


                req = "escortInfoService";

                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("escortInfoLangue")){

            String idEscort = params[1];

            try {
                URL url = new URL(escortInfoLangue_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("idEscort","UTF-8")+"="+URLEncoder.encode(idEscort,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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


                req = "escortInfoLangue";

                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(type.equals("registerEscort")){
            String regEmail = params[1];
            String regPassword = params[2];
            String regPseudo = params[3];
            String regNom = params[4];
            String regPrenom = params[5];
            String regCoruplence = params[6];
            String regTaille = params[7];
            String regDescription = params[8];
            String regCheveux = params[9];
            String regOrigine = params[10];
            String regYeux = params[11];
            String regAdresse = params[12];

            try {
                URL url = new URL(registerEscort_url);
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
                outputStream.close();
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


                req = "registerEscort";

                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
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

        String flag = req;
        // String flag = mPreferences.getString("flag","0");

        if(flag.equals("login")){
            String test = "false";
            String password = "";
            String email = "";
            String[] serverResponse = result.split("[,]");
            test = serverResponse[0];
            email = serverResponse[1];
            password = serverResponse[2];

            if(test.equals("true")){

                Intent intent = new Intent(context,NavigationActivity.class);
                context.startActivity(intent);
                LoginActivity.fa.finish();
                //Toast.makeText(context,"Connection réussie",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"Indentifiant incorrect",Toast.LENGTH_LONG).show();
            }
        }
        else if(flag.equals("register")) {

            String message = "Erreur";
            if (result.equals("ok"))
            {
                message ="Okay";
            }
            else if (result.equals("erreur"))
            {
                message ="Erreur register";
            }
            else if (result.equals("existant"))
            {
                message ="Email déjà utilisé";
            }
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }
        else if(flag.equals("afficheEscort")) {
            String[] splitArray = null;
            String[] splitArray2 = null;

            splitArray = result.split("true");
            System.out.println("slipArray ============================== "+splitArray.length);

            for(int i = 1; i< splitArray.length;i++){
                splitArray2 = splitArray[i].split(",");
                System.out.println("slipArray2[1] ="+splitArray2[1]);
                System.out.println("slipArray2[2] ="+splitArray2[2]);
                System.out.println("slipArray2[3] ="+splitArray2[3]);

                ((NavigationActivity) context).list.add(new Femme(splitArray2[3], splitArray2[1],Integer.parseInt(splitArray2[2])));
                ((NavigationActivity) context).updateListGirl();
                splitArray2=null;

            }
            //context = (NavigationActivity) context;

        }
        else if(flag.equals("escortInfo")) {

            String[] splitArray = null;

            splitArray = result.split("4uSXa3k9");

            ((DetailActivity) context).chargerDetail(splitArray[1],splitArray[2],splitArray[3],splitArray[4],splitArray[5],splitArray[6],splitArray[7],splitArray[8],splitArray[9],splitArray[10],splitArray[11],splitArray[12],splitArray[13]);
            //Toast.makeText(context,splitArray[1]+splitArray[2],Toast.LENGTH_LONG).show();

        }
        else if(flag.equals("escortInfoPhoto")) {

            String[] splitArray = null;
            String[] splitArray2 = null;

            splitArray = result.split("true");

            System.out.println("================ escortInfoPhoto =======");

            ArrayList<String> listPhoto = new ArrayList<>();

            for(int i = 1; i< splitArray.length;i++){
                splitArray2 = splitArray[i].split(",");
                System.out.println("slipArray2[1] ="+splitArray2[1]);// la photo
                System.out.println("slipArray2[2] ="+splitArray2[2]);// si certifié
                listPhoto.add(splitArray2[1]);
                splitArray2=null;

            }

            //((DetailActivity) context).imageUrls = listPhoto;
            ((DetailActivity) context).chargerPhoto(listPhoto);
            //Toast.makeText(context,splitArray.length,Toast.LENGTH_LONG).show();

        }
        else if(flag.equals("escortInfoService")) {

            String[] splitArray = null;
            String[] splitArray2 = null;

            splitArray = result.split("true");

            System.out.println("================ escortInfoService =======");

            ArrayList<String> listService = new ArrayList<>();

            for(int i = 1; i< splitArray.length;i++){
                splitArray2 = splitArray[i].split(",");
                listService.add(splitArray2[1]);
                splitArray2=null;

            }

            //((DetailActivity) context).imageUrls = listPhoto;
            ((DetailActivity) context).chargerService(listService);
            //Toast.makeText(context,splitArray.length,Toast.LENGTH_LONG).show();

        }
        else if(flag.equals("escortInfoLangue")) {

            String[] splitArray = null;
            String[] splitArray2 = null;

            splitArray = result.split("true");

            System.out.println("================ escortInfoLangue =======");

            ArrayList<String> listLangue = new ArrayList<>();
            ArrayList<String> listLangueNote = new ArrayList<>();

            for(int i = 1; i< splitArray.length;i++){
                splitArray2 = splitArray[i].split(",");
                System.out.println("langue ="+splitArray2[1]);//la langue
                System.out.println("note ="+splitArray2[2]);// la note de la langue
                listLangue.add(splitArray2[1]);
                listLangueNote.add(splitArray2[2]);
                splitArray2=null;

            }

            ((DetailActivity) context).chargerLangue(listLangue,listLangueNote);

        }
        else
        {
            Toast.makeText(context,"Erreur flag =>"+flag,Toast.LENGTH_LONG).show();
        }

        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
