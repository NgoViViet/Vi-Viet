package com.example.demojson;

import android.util.JsonWriter;

import java.io.IOException;
import java.io.Writer;

public class JsonWriterExample {


    public static void writeJsonStream(Writer output, company company ) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(output);

        jsonWriter.beginObject();// begin root

        jsonWriter.name("id").value(company.getId());
        jsonWriter.name("name").value(company.getName());

        String[] websites= company.getWebsites();

        // "websites": [ ....]
        jsonWriter.name("websites").beginArray(); // begin websites
        for(String website: websites) {
            jsonWriter.value(website);
        }
        jsonWriter.endArray();// end websites

        // "address": { ... }
        jsonWriter.name("address").beginObject(); // begin address
        jsonWriter.name("street").value(company.getAddress().getStreet());
        jsonWriter.name("city").value(company.getAddress().getCity());
        jsonWriter.endObject();// end address

        // end root
        jsonWriter.endObject();
    }


    public static company createCompany() {

        company company = new company();
        company.setId(123);
        company.setName("Apple");

        String[] websites = { "http://apple.com", "https://jobs.apple.com" };
        company.setWebsites(websites);

        address address = new address();
        address.setCity("Cupertino");
        address.setStreet("1 Infinite Loop");

        company.setAddress(address);

        return company;
    }

}