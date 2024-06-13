package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.PedidoFacade;
import com.entidades.buenSabor.business.mapper.PedidoMapper;
import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.PreferenceMp;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/mp")
public class MercadoPagoController {
    @Autowired
    PedidoFacade pedidoFacade;
    @Autowired
    PedidoMapper pedidoMapper;

    @PostMapping("/create_preference")
    public PreferenceMp getPreferenciaIdMercadoPago(PedidoPostDto pedido){
        try{
            Pedido pedidoSaved = pedidoMapper.toEntity(pedidoFacade.createNew(pedido));
            MercadoPagoConfig.setAccessToken("TEST-3240024548426588-052416-79f1314bbfa3e3005597f17ee3e79039-613409765");
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(pedidoSaved.getId().toString())
                    .title("Pedido de "+pedidoSaved.getCliente().getNombre())
                    .description("Pedido del dia "+pedidoSaved.getFechaPedido())
                    .pictureUrl("url")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(pedidoSaved.getTotal()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backUrl = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/")
                    .pending("http://localhost:5173/").failure("http://localhost:5173/").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrl)
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceMp mpPreference = new PreferenceMp();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
