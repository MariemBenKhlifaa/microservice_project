package tn.esprit.reclamationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.entity.Reponse;
import tn.esprit.reclamationservice.repo.ReclamationRepository;
import tn.esprit.reclamationservice.repo.ReponseRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class ReponseService implements ReponseInterface {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private ReponseRepository reponseRepository;

    @Override
    public Reponse addReponse(Long idReclamation, Reponse reponse, MultipartFile pieceJointe) {
        Reclamation reclamation = reclamationRepository.findById(idReclamation).orElseThrow(() -> new RuntimeException("reclamation not found"));
        reponse.setReclamation(reclamation);
        reclamation.setEtat(true);
        return reponseRepository.save(reponse);
    }

    @Override
    public Reponse getReponse(Long reclamationId) {
        return reponseRepository.findByReclamationIdReclamation(reclamationId);
    }

}
