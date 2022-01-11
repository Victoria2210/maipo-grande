package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Cliente;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.modelos.Transportista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUsuarioLoginServicio implements UserDetailsService {

    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProductorServicio productorServicio;
    @Autowired
    TransportistaServicio transportistaServicio;
    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Cliente cliente = clienteServicio.buscarClientePorEmail(s);
        Productor productor = productorServicio.buscarProductorPorEmail(s);
        Transportista transportista = transportistaServicio.buscarTransportistaPorEmail(s);
        if (cliente != null && productor == null && transportista == null) {
            if (cliente.getTipoCli() == 'E') {
                authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE_EXTERNO"));
                session.setAttribute("clienteExterno", cliente);
                session.setAttribute("tipo", cliente.getTipoCli());
                session.setAttribute("nombre", cliente.getNombreCli());
                session.setAttribute("apellido", cliente.getApellidosCli());
            } else if (cliente.getTipoCli() == 'I') {
                authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE_INTERNO"));
                session.setAttribute("clienteInterno", cliente);
                session.setAttribute("tipo", cliente.getTipoCli());
                session.setAttribute("nombre", cliente.getNombreCli());
                session.setAttribute("apellido", cliente.getApellidosCli());
            }
            return new User(cliente.getIdCli().toString(), cliente.getContraseñaCli(), true, true, true, true, authorities);
        } else if (cliente == null && productor != null && transportista == null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PRODUCTOR"));
            session.setAttribute("productor", productor);
            session.setAttribute("nombre", productor.getNombreProd());
            session.setAttribute("apellido", productor.getApellidoProd());
            return new User(productor.getIdProd().toString(), productor.getContraseñaProd(), true, true, true, true, authorities);
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_TRANSPORTISTA"));
            List<Integer> ventasActivas = transportistaServicio.ventasActivasTran(transportista.getIdTran());
            session.setAttribute("ventasActivas", ventasActivas);
            session.setAttribute("transportista", transportista);
            session.setAttribute("nombre", transportista.getNombreTran());
            session.setAttribute("apellido", transportista.getApellidosTran());
            return new User(transportista.getIdTran().toString(), transportista.getContraseñaTran(), true, true, true, true, authorities);
        }

    }
}
