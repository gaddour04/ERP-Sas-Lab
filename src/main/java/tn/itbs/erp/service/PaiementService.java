package tn.itbs.erp.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.entites.Paiement;
import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.exception.FactureNotFoundException;
import tn.itbs.erp.exception.PaiementNotFoundException;
import tn.itbs.erp.repository.PaiementRepository;
@Service
@Transactional
public class PaiementService implements IPaiementService{
	@Autowired
	PaiementRepository paiementRepository;
	@Autowired
	IFactureService factureService;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
	int i=1;
	
	@Override
	public List<Paiement> findAllFournisseur() {	
		return paiementRepository.findAllPaiementFournisseur();
	}
	
	@Override
	public Page<Paiement> findAllFournisseurPagination(int p, int s) {
		return paiementRepository.findAllPaiementFournisseur(PageRequest.of(p, s));
	}
	@Override
	public List<Paiement> findAllClient() {	
		return paiementRepository.findAllPaiementClient();
	}
	
	@Override
	public Page<Paiement> findAllClientPagination(int p, int s) {
		return paiementRepository.findAllPaiementClient(PageRequest.of(p, s));
	}
	

	@Override
	public Paiement save(Paiement p) {
		p.setDatePayement(new Date());
		Facture f;
		try {
			
			f = factureService.findById(p.getFacture().getId()).get();
			f.setEtatFacture("partiellement payé");
			double montantPaye = 0;
			int x=f.getPaiements().size();
			for(int i=0;i<x;i++) {		
				montantPaye+=f.getPaiements().get(i).getMontant();
				//System.out.println(f.getPaiements().get(i).getMontant());
				//System.out.println("montant paye fel boucle"+montantPaye);
			}
			
			//System.out.println(montantPaye);
			double montantPayeTotal=montantPaye+p.getMontant();
			if(montantPayeTotal==f.getTotal()) {
				f.setEtatFacture("payé");
			}
			Date dateAjourdhui = new Date();  
			String Ajourdhui= format.format(dateAjourdhui);
			Date dateHier = new Date(new Date().getTime() - 86400000);
			if(f.getClient()!=null) {
				String lastNumeroFacture=paiementRepository.lastNumeroPaiementClient();
				System.out.println(lastNumeroFacture);
				if(lastNumeroFacture ==null) {
					i=1;
				}
				//System.out.println(lastNumeroFacture);
				else {
					String lastFourDigits = "";     //substring containing last 4 characters
					 
					int index= lastNumeroFacture.lastIndexOf("/");
					//System.out.println(index);
					lastFourDigits = lastNumeroFacture.substring( index+1);
					//System.out.println(lastFourDigits);
					i=Integer.parseInt(lastFourDigits);
					i++;
				}
				
				//System.out.println(i);
				if(dateAjourdhui.getMonth()!=dateHier.getMonth()) {
					i=1;
				}
				
				String numero=String.format("%04d", i);
				String numeroPaiement="PAIC/"+Ajourdhui+"/"+numero;
				p.setNumeroPaiement(numeroPaiement);
			}
			else if(f.getFournisseur()!=null) {
				String lastNumeroFactureFournisseur=paiementRepository.lastNumeroPaiementFournisseur();
				System.out.println(lastNumeroFactureFournisseur);
				if(lastNumeroFactureFournisseur ==null) {
					i=1;
				}
				//System.out.println(lastNumeroFacture);
				else {
					String lastFourDigits = "";     //substring containing last 4 characters
					 
					int index= lastNumeroFactureFournisseur.lastIndexOf("/");
					//System.out.println(index);
					lastFourDigits = lastNumeroFactureFournisseur.substring( index+1);
					//System.out.println(lastFourDigits);
					i=Integer.parseInt(lastFourDigits);
					i++;
				}
				
				//System.out.println(i);
				if(dateAjourdhui.getMonth()!=dateHier.getMonth()) {
					i=1;
				}
				
				String numero=String.format("%04d", i);
				String numeroPaiement="PAIF/"+Ajourdhui+"/"+numero;
				p.setNumeroPaiement(numeroPaiement);
			}
			//p.setNameImage(p.getNumeroPaiement());
			/*if(!file.isEmpty()) {
			p.setImage(compressBytes(file.getBytes()));
			}*/
			
		} catch (FactureNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return paiementRepository.save(p);
		
	}

	

	
	@Override
	public Optional<Paiement> findById(long id) throws PaiementNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(long id) throws PaiementNotFoundException {
		// TODO Auto-generated method stub
		Paiement paiement=paiementRepository.findById(id).orElseThrow(()->new PaiementNotFoundException(id));
		
			/*Facture facture=factureService.findById(paiement.getFacture().getId()).get();
			facture.setTotal(facture.getTotal()-paiement.getMontant());
			if(facture.getClient()!=null)
				factureService.saveFactureClient(facture);
			else
				factureService.saveFactureFournisseur(facture);*/
			paiementRepository.delete(paiement);
		
		
	}

	@Override
	public Paiement update(long id, Paiement paiementDetails) throws PaiementNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}*/

	@Override
	public void saveImage(long id, MultipartFile file) throws PaiementNotFoundException, IOException {
		
		Paiement paiement=paiementRepository.findById(id)
				.orElseThrow(()-> new PaiementNotFoundException(id));
		
		//paiement.setImage(compressBytes(file.getBytes()));
		String path = "C:/Users/gaddo/Desktop/Study/angular-projects/erp/src/assets/imagePaiements";
				File pathAsFile = new File(path);

				if (!Files.exists(Paths.get(path))) {
					pathAsFile.mkdir();
				}
				if(!file.isEmpty()) {
					
					String paiementImage=paiement.getNumeroPaiement().replaceAll("/", "-");
					file.transferTo(new File(path+'/'+paiementImage+".png"));
				}
		
		//C:\Users\gaddo\Desktop\Study\angular-projects\erp\src\assets
	}

	

}
