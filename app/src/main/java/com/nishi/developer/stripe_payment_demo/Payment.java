package com.nishi.developer.stripe_payment_demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;


public class Payment extends AppCompatActivity {

    CardInputWidget mCardInputWidget;
    Context context;
    Button btn_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        context = Payment.this;

        mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);


        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Card cardToSave = mCardInputWidget.getCard();
                if (cardToSave == null) {
                    Toast.makeText(context, "Invalid Card Data", Toast.LENGTH_SHORT).show();
                }

                /*  change publish key as per your account. current key from rutvij sir account  */
                Stripe stripe = new Stripe(context, "pk_test_p5cGxTshvHHO1QIBlSXv1dUx");
                stripe.createToken(
                        cardToSave,
                        new TokenCallback() {
                            public void onSuccess(Token token) {
                                // Send token to your server

                                Log.e("token for " , String.valueOf(token.getId()));


                                Toast.makeText(context,"success" + token, Toast.LENGTH_LONG).show();
                            }

                            public void onError(Exception error) {
                                // Show localized error message
                                Toast.makeText(context, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );

            }
        });


    }
}
