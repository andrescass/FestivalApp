package com.camacuasoft.jazzfestivalapp.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.camacuasoft.jazzfestivalapp.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFragment extends Fragment {

    private final String mp_client_id = "4009089907503778";
    private final String mp_client_secret ="h0pZsAQaz4PN3Yu68hP0Dc8z54XyQjh6";
    private final String mp_app_short = "mp-app-18509939";

    private final int PAYPAL_REQUEST_CODE = 523;

    private final String mp_donate_25 = "http://mpago.la/NFqR";
    private final String mp_donate_50 = "http://mpago.la/5lAo";
    private final String mp_donate_100 = "http://mpago.la/Ou6y";

    public DonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation, container, false);

        //** Bind views
        ImageButton mpButton = (ImageButton) view.findViewById(R.id.donation_mp_button);
        ImageButton ppButton = (ImageButton) view.findViewById(R.id.donation_pp_button);

        //** Load button images
        Picasso.with(getContext()).load(R.drawable.logo_mercadopago).fit().into(mpButton);
        Picasso.with(getContext()).load(R.drawable.paypal_logo_blanck).fit().into(ppButton);

        mpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpButtonPressed();
            }
        });

        ppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a basic PayPal payment
                PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf("5")), "USD", "Simplified Coding Fee",
                        PayPalPayment.PAYMENT_INTENT_SALE);

                PayPalConfiguration config = new PayPalConfiguration()
                        // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                        // or live (ENVIRONMENT_PRODUCTION)
                        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                        .clientId("AQLXarUFB6D7yhMwuCu6y2UT-s2ZHM9ew_wRg9bT9oABNTKBdEPCtZSJraMaUeUATyN9y3Eif-mGfOd0");

                Intent intent = new Intent(getActivity(), PaymentActivity.class);

                //putting the paypal configuration to the intent
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                //Puting paypal payment to the intent
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

                //Starting the intent activity for result
                //the request code will be used on the method onActivityResult
                startActivityForResult(intent, PAYPAL_REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Toast.makeText(getActivity(), paymentDetails, Toast.LENGTH_SHORT).show();
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        /*startActivity(new Intent(this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));*/

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    private void mpButtonPressed() {
        CharSequence mpChoices[] = new CharSequence[] {"$25", "$50", "$100"};

        final AlertDialog.Builder mpChoiceBuilder = new AlertDialog.Builder(getContext());
        mpChoiceBuilder.setTitle("Seleccione un monto");
        mpChoiceBuilder.setItems(mpChoices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selectedUrl = "";
                boolean donating = false;
                switch (i){
                    case 0:
                        selectedUrl = mp_donate_25;
                        donating = true;
                        break;
                    case 1:
                        selectedUrl = mp_donate_50;
                        donating = true;
                        break;
                    case 2:
                        selectedUrl = mp_donate_100;
                        donating = true;
                        break;
                }

                if(donating){
                    Intent webIntent = new Intent(Intent.ACTION_VIEW);
                    webIntent.setData(Uri.parse(selectedUrl));
                    startActivity(webIntent);
                }
            }
        });
        mpChoiceBuilder.show();
    }

}
