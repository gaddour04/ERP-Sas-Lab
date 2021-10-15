package tn.itbs.erp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.entites.LigneFacture;
import tn.itbs.erp.entites.Paiement;
import tn.itbs.erp.exception.FactureNotFoundException;
import tn.itbs.erp.exception.PaiementNotFoundException;
import tn.itbs.erp.repository.ArticleRepository;
import tn.itbs.erp.repository.FactureRepository;
import tn.itbs.erp.repository.LigneFactureReoisitory;

@Service
@Transactional
public class FactureService implements IFactureService{
	@Autowired FactureRepository factureRepository;
	@Autowired ArticleRepository articleRepository;
	@Autowired LigneFactureReoisitory ligneFactureReoisitory;

	double sum = 0;
	double tva=0;
	double sumupdate=0;
	double tvaupdate=0;
	double timbre=0.650;
	 int i=1;
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
	@Override
	public Facture saveFactureClient(Facture facture) {
		sum=0;
		tva=0;

		facture.setDatefacture(new Date());
		facture.getLignefactures().forEach(f->{
			System.out.println("ligne 43");
			System.out.println(f.getArticle().getCode());
			Article a=articleRepository.findById(f.getArticle().getCode()).get();
			System.out.println(a.getPrix());
			f.setArticle(a);
		});
		facture.getLignefactures().forEach(f->{

			try {
				f.setTotal(f.getArticle().getPrix()*f.getQte());
				Article a=articleRepository.findById(f.getArticle().getCode()).get();
				a.setQte(a.getQte()-f.getQte());
				sum+= f.getTotal();

			} catch (Exception e) {
				// TODO: handle exception
			}

		});


		facture.getLignefactures().forEach(r->r.setFacture(facture));
		//System.out.println("sum ==****== "+sum);
		facture.setTotal_unitaire(sum);
		tva=(sum*19)/100;

		facture.setTva(tva);

		facture.setTimbre(timbre);
		facture.setTotal(timbre+tva+sum);
		// numero facture ou bien code facture 
		Date dateAjourdhui = new Date();  
		String Ajourdhui= format.format(dateAjourdhui);
		Date dateHier = new Date(new Date().getTime() - 86400000);
		String lastNumeroFacture=factureRepository.lastNumeroFactureClient();
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
		String numeroFacture="FACTC/"+Ajourdhui+"/"+numero;
		facture.setNumeroFacture(numeroFacture);
		
		/// etat de facture 
		facture.setEtatFacture("non payé");
		//System.out.println(factureRepository.lastNumeroFactureClient());

		return factureRepository.save(facture);
	}

	@Override
	public List<Facture> findAllFactureClient() {
		return factureRepository.findAllByClientNotNull();
	}
	@Override
	public List<Facture> findAllFactureFournisseur() {
		return factureRepository.findAllByFournisseurNotNull();
	}

	@Override
	public Optional<Facture> findById(long id) throws FactureNotFoundException {
		// TODO Auto-generated method stub
		return factureRepository.findById(id);
	}

	@Override
	public void deleteById(long id) throws FactureNotFoundException {
		Facture facture=factureRepository.findById(id).orElseThrow(()-> new FactureNotFoundException(id));


		factureRepository.delete(facture);

	}

	@Override
	public Facture update(long id, Facture factureDetails) throws FactureNotFoundException {
		sumupdate=0;
		tvaupdate=0;
		Facture facture=factureRepository.findById(id).orElseThrow(()-> new FactureNotFoundException(id));
		facture.setId(id);
		facture.setClient(factureDetails.getClient());
		//facture.setLignefactures(factureDetails.getLignefactures());
		
		
		// 7ata nchouf fel front kifah 
		facture.getLignefactures().forEach(ligne->{
			Article a=articleRepository.findById(ligne.getArticle().getCode()).get();
			a.setQte(ligne.getQte()+a.getQte());
			ligneFactureReoisitory.deleteById(ligne.getId());
		});

		//facture.setDatefacture(new Date());
		factureDetails.getLignefactures().forEach(f->{

			try {
				System.out.println(f.getQte() +" ====>ligne 123");
				Article a=articleRepository.findById(f.getArticle().getCode()).get();
				facture.getLignefactures().forEach(ligne->{
					f.setId(ligne.getId());
				});
				f.setArticle(a);
				f.setTotal(f.getArticle().getPrix()*f.getQte());
				//Article a=articleRepository.findById(f.getArticle().getCode()).get();
				System.out.println(a.getQte() +" ====>ligne 125");
				a.setQte(a.getQte()-f.getQte());
				sumupdate+= f.getTotal();
				System.out.println(sumupdate);
			

			} catch (Exception e) {}
		});
		facture.setLignefactures(factureDetails.getLignefactures());
		facture.getLignefactures().forEach(r->r.setFacture(facture));
		//System.out.println("sum ==****== "+sum);
		facture.setTotal_unitaire(sumupdate);
		tvaupdate=(sumupdate*19)/100;
		facture.setTva(tvaupdate);
		double timbre=0.650;
		//facture.setTimbre(timbre);
		facture.setTotal(timbre+tvaupdate+sumupdate);
		sum=0;
		tva=0;
		return factureRepository.save(facture);
	}

	@Override
	public Page<Facture> findAllFactureClientPagination(int p, int s) {
		return factureRepository.findAllByClientNotNull(PageRequest.of(p, s));
	}
	@Override
	public Page<Facture> findAllFactureFournisseurPagination(int p, int s) {
		return factureRepository.findAllByFournisseurNotNull(PageRequest.of(p, s));
	}
	
	@Override
	public Facture saveFactureFournisseur(Facture facture) {
		sum=0;
		tva=0;

		facture.setDatefacture(new Date());
		facture.getLignefactures().forEach(f->{
			System.out.println("ligne 43");
			System.out.println(f.getArticle().getCode());
			Article a=articleRepository.findById(f.getArticle().getCode()).get();
			System.out.println(a.getPrix());
			f.setArticle(a);
		});
		facture.getLignefactures().forEach(f->{

			try {
				f.setTotal(f.getArticle().getPrix()*f.getQte());
				Article a=articleRepository.findById(f.getArticle().getCode()).get();
				a.setQte(a.getQte()+f.getQte());
				sum+= f.getTotal();

			} catch (Exception e) {
				// TODO: handle exception
			}

		});


		facture.getLignefactures().forEach(r->r.setFacture(facture));
		//System.out.println("sum ==****== "+sum);
		facture.setTotal_unitaire(sum);
		tva=(sum*19)/100;

		facture.setTva(tva);

		facture.setTimbre(timbre);
		facture.setTotal(timbre+tva+sum);
		// numero facture ou bien code facture 
		Date dateAjourdhui = new Date();  
		String Ajourdhui= format.format(dateAjourdhui);
		Date dateHier = new Date(new Date().getTime() - 86400000);
		String lastNumeroFacture=factureRepository.lastNumeroFactureFournisseur();
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
		String numeroFacture="FACTF/"+Ajourdhui+"/"+numero;
		facture.setNumeroFacture(numeroFacture);
		
		/// etat de facture 
		facture.setEtatFacture("non payé");
		//System.out.println(factureRepository.lastNumeroFactureClient());

		
				factureRepository.save(facture);
				return  facture;
	}
	@Override
	public void saveImage(long id, MultipartFile file) throws FactureNotFoundException, IOException {
		
		Facture facture=factureRepository.findById(id)
				.orElseThrow(()-> new FactureNotFoundException(id));
		
		//paiement.setImage(compressBytes(file.getBytes()));
		String path = "C:/Users/gaddo/Desktop/Study/angular-projects/erp/src/assets/imageFactureFournisseur";
				File pathAsFile = new File(path);

				if (!Files.exists(Paths.get(path))) {
					pathAsFile.mkdir();
				}
				if(!file.isEmpty()) {
					
					String paiementImage=facture.getNumeroFacture().replaceAll("/", "-");
					file.transferTo(new File(path+'/'+paiementImage+".png"));
				}
		
		//C:\Users\gaddo\Desktop\Study\angular-projects\erp\src\assets
	}

}
