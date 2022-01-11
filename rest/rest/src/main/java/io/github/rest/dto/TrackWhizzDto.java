package io.github.rest.dto;

import java.util.Date;
import java.util.List;

public class TrackWhizzDto {

    public class SegmentDetail {
        public String Segment;
    }

    public class Segments {
        public SegmentDetail SegmentDetail;
    }

    public class CountryDetail {
        public String Country;
    }

    public class Citizenship {
        public CountryDetail CountryDetail;
    }

    public class NatureOfBusinessDetail {
        public String Business;
    }

    public class NatureOfBusiness {
        public List<NatureOfBusinessDetail> NatureOfBusinessDetail;
    }

    public class EmailDetail {
        public String EmailType;
        public String EmailId;
    }

    public class EmailDetails {
        public List<EmailDetail> EmailDetail;
    }

    public class AddressDetail {
        public String AddressType;
        public String AddressLine1;
        public String AddressLine2;
        public String PinCode;
        public String City;
        public String State;
        public String Country;
        public String District;
    }

    public class AddressDetails {
        public List<AddressDetail> AddressDetail;
    }

    public class TaxIdentificationDetail {
        public String TaxIdentificationNumber;
        public String Country;
        public Date StartDate;
    }

    public class TaxIdentificationDetails {
        public TaxIdentificationDetail TaxIdentificationDetail;
    }

    public class ContactDetail {
        public String ContactType;
        public String ISDCode;
        public String STDCode;
        public String ContactNumber;
    }

    public class ContactDetails {
        public List<ContactDetail> ContactDetail;
    }

    public class IdentificationDetail {
        public String IdType;
        public String IdNumber;
        public Date ExpiryDate;
        public String IssueCountry;
    }

    public class IdentificationDetails {
        public List<IdentificationDetail> IdentificationDetail;
    }

    public class ProductSegmentDetail {
        public String ProductSegment;
    }

    public class ProductSegments {
        public ProductSegmentDetail ProductSegmentDetail;
    }

    public class Nationality {
        public CountryDetail CountryDetail;
    }

    public class CountryOfOperation {
        public CountryDetail CountryDetail;
    }

    public class PEPClassificationDetail {
        public String Classification;
    }

    public class PEPClassification {
        public PEPClassificationDetail PEPClassificationDetail;
    }

    public class AdverseMediaClassificationDetail {
        public String Classification;
    }

    public class AdverseMediaClassification {
        public AdverseMediaClassificationDetail AdverseMediaClassificationDetail;
    }

    public class TagDetail {
        public String Tag;
    }

    public class Tags {
        public TagDetail TagDetail;
    }

    public class ReputationClassificationDetail {
        public String Classification;
    }

    public class ReputationClassification {
        public ReputationClassificationDetail ReputationClassificationDetail;
    }

    public class RelationDetail {
        public String RecordIdentifier;
        public int Relation;
        public Date RelationStartDate;
    }

    public class RelationDetails {
        public List<RelationDetail> RelationDetail;
    }

    public class RecordRequestDetail {
        public String TransactionId;
        public String RecordIdentifier;
        public int ProposedAs;
        public Segments Segments;
        public String CustomerType;
        public String CustomerSubType;
        public String Prefix;
        public String FirstName;
        public String MiddleName;
        public String LastName;
        public String Gender;
        public String MaritalStatus;
        public Citizenship Citizenship;
        public String OccupationType;
        public NatureOfBusiness NatureOfBusiness;
        public String NatureOfBusinessOther;
        public Date DateofBirth;
        public EmailDetails EmailDetails;
        public AddressDetails AddressDetails;
        public String PermanentAddressProof;
        public String CountryOfBirth;
        public String BirthCity;
        public TaxIdentificationDetails TaxIdentificationDetails;
        public ContactDetails ContactDetails;
        public IdentificationDetails IdentificationDetails;
        public String ProofOfIDSubmitted;
        public int Minor;
        public ProductSegments ProductSegments;
        public String ApplicationRefNumber;
        public String IntermediaryCode;
        public int Listed;
        public String Industry;
        public Nationality Nationality;
        public CountryOfOperation CountryOfOperation;
        public String IncomeRange;
        public int ExactIncome;
        public String IncomeCurrency;
        public Date IncomeEffectiveDate;
        public String IncomeDescription;
        public String IncomeProof;
        public int Networth;
        public String NetworthCurrency;
        public Date NetworthEffectiveDate;
        public String NetworthDescription;
        public String NetworthProof;
        public String PEP;
        public PEPClassification PEPClassification;
        public int AdverseMedia;
        public AdverseMediaClassification AdverseMediaClassification;
        public String AdverseMediaDetails;
        public Tags Tags;
        public String Channel;
        public String Links;
        public ReputationClassification ReputationClassification;
        public RelationDetails RelationDetails;
        public RegAMLSpecialCategoryDetails RegAMLSpecialCategoryDetails;
        public RMDetails RMDetails;
    }

    public class RegAMLSpecialCategoryDetail {
        public String RegAMLSpecialCategory;
        public Date StartDate;
        public Object EndDate;
    }

    public class RegAMLSpecialCategoryDetails {
        public RegAMLSpecialCategoryDetail RegAMLSpecialCategoryDetail;
    }

    public class RMDetail {
        public String UserCode;
        public String Type;
        public Date FromDate;
    }

    public class RMDetails {
        public RMDetail RMDetail;
    }

    public class RecordRequestDetails {
        public List<RecordRequestDetail> RecordRequestDetail;
    }

    public class RiskProfilingRequest {
        public String ApiToken;
        public String RequestId;
        public int RiskCalculationFor;
        public String ParentCompany;
        public RecordRequestDetails RecordRequestDetails;
        public String xsd;
        public String xsi;
        public String text;
    }


}
