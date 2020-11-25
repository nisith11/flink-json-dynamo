package org.edu.nisith.pojo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

/**
 * Created by nisith11 on 23/11/2020.
 */

@DynamoDBTable(tableName="Transactions")
public class TransactionRecordFormat {

    @DynamoDBHashKey(attributeName="messageKey")
    @JsonProperty(value="message_key")
    private String messageKey;

    @DynamoDBAttribute(attributeName = "outboxPublishedDate")
    @JsonProperty(value="outbox_published_date")
    private String outboxPublishedDate;

    @DynamoDBAttribute(attributeName = "createdDate")
    @JsonProperty(value="created_date")
    private String createdDate;

    @DynamoDBAttribute(attributeName = "updatedDate")
    @JsonProperty(value="updated_date")
    private String updatedDate;

    @DynamoDBAttribute(attributeName = "eventType")
    @JsonProperty(value="event_type")
    private String eventType;

    @DynamoDBAttribute(attributeName = "eventTypeKeyName")
    @JsonProperty(value="event_type_key_name")
    private String eventTypeKeyName;

    @DynamoDBAttribute(attributeName = "eventMetadataVersion")
    @JsonProperty(value="event_metadata_version")
    private String eventMetadataVersion;

    @DynamoDBAttribute(attributeName = "transactionId")
    @JsonProperty(value="transaction_id")
    private String transactionId;

    @DynamoDBAttribute(attributeName = "correlationId")
    @JsonProperty(value="correlation_id")
    private String correlationId;

    @DynamoDBAttribute(attributeName = "subscriptionKey")
    @JsonProperty(value="subscription_key")
    private String subscriptionKey;

    @DynamoDBAttribute(attributeName = "isCredit")
    @JsonProperty(value="is_credit")
    private String isCredit;

    @DynamoDBAttribute(attributeName = "amount")
    @JsonProperty(value="amount")
    private String amount;

    @DynamoDBAttribute(attributeName = "currency")
    @JsonProperty(value="currency")
    private String currency;

    @DynamoDBAttribute(attributeName = "postedBalance")
    @JsonProperty(value="posted_balance")
    private String postedBalance;

    @DynamoDBAttribute(attributeName = "postedTimestampUtc")
    @JsonProperty(value="posted_timestamp_utc")
    private String postedTimestampUtc;

    @DynamoDBAttribute(attributeName = "state")
    @JsonProperty(value="state")
    private String state;

    @DynamoDBAttribute(attributeName = "transactionCode")
    @JsonProperty(value="transaction_code")
    private String transactionCode;

    @DynamoDBAttribute(attributeName = "lastUpdated")
    @JsonProperty(value="last_updated")
    private String lastUpdated;


    public TransactionRecordFormat() {
    }

    public TransactionRecordFormat(String messageKey, String outboxPublishedDate, String createdDate, String updatedDate, String eventType, String eventTypeKeyName, String eventMetadataVersion, String transactionId, String correlationId, String subscriptionKey, String isCredit, String amount, String currency, String postedBalance, String postedTimestampUtc, String state, String transactionCode, String lastUpdated) {
        this.messageKey = messageKey;
        this.outboxPublishedDate = outboxPublishedDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.eventType = eventType;
        this.eventTypeKeyName = eventTypeKeyName;
        this.eventMetadataVersion = eventMetadataVersion;
        this.transactionId = transactionId;
        this.correlationId = correlationId;
        this.subscriptionKey = subscriptionKey;
        this.isCredit = isCredit;
        this.amount = amount;
        this.currency = currency;
        this.postedBalance = postedBalance;
        this.postedTimestampUtc = postedTimestampUtc;
        this.state = state;
        this.transactionCode = transactionCode;
        this.lastUpdated = lastUpdated;
    }

    public String getEventMetadataVersion() {
        return eventMetadataVersion;
    }

    public void setEventMetadataVersion(String eventMetadataVersion) {
        this.eventMetadataVersion = eventMetadataVersion;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getOutboxPublishedDate() {
        return outboxPublishedDate;
    }

    public void setOutboxPublishedDate(String outboxPublishedDate) {
        this.outboxPublishedDate = outboxPublishedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTypeKeyName() {
        return eventTypeKeyName;
    }

    public void setEventTypeKeyName(String eventTypeKeyName) {
        this.eventTypeKeyName = eventTypeKeyName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(String subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    public String getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPostedBalance() {
        return postedBalance;
    }

    public void setPostedBalance(String postedBalance) {
        this.postedBalance = postedBalance;
    }

    public String getPostedTimestampUtc() {
        return postedTimestampUtc;
    }

    public void setPostedTimestampUtc(String postedTimestampUtc) {
        this.postedTimestampUtc = postedTimestampUtc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionRecordFormat)) return false;

        TransactionRecordFormat that = (TransactionRecordFormat) o;

        if (getMessageKey() != null ? !getMessageKey().equals(that.getMessageKey()) : that.getMessageKey() != null)
            return false;
        if (getOutboxPublishedDate() != null ? !getOutboxPublishedDate().equals(that.getOutboxPublishedDate()) : that.getOutboxPublishedDate() != null)
            return false;
        if (getCreatedDate() != null ? !getCreatedDate().equals(that.getCreatedDate()) : that.getCreatedDate() != null)
            return false;
        if (getUpdatedDate() != null ? !getUpdatedDate().equals(that.getUpdatedDate()) : that.getUpdatedDate() != null)
            return false;
        if (getEventType() != null ? !getEventType().equals(that.getEventType()) : that.getEventType() != null)
            return false;
        if (getEventTypeKeyName() != null ? !getEventTypeKeyName().equals(that.getEventTypeKeyName()) : that.getEventTypeKeyName() != null)
            return false;
        if (getEventMetadataVersion() != null ? !getEventMetadataVersion().equals(that.getEventMetadataVersion()) : that.getEventMetadataVersion() != null)
            return false;
        if (getTransactionId() != null ? !getTransactionId().equals(that.getTransactionId()) : that.getTransactionId() != null)
            return false;
        if (getCorrelationId() != null ? !getCorrelationId().equals(that.getCorrelationId()) : that.getCorrelationId() != null)
            return false;
        if (getSubscriptionKey() != null ? !getSubscriptionKey().equals(that.getSubscriptionKey()) : that.getSubscriptionKey() != null)
            return false;
        if (getIsCredit() != null ? !getIsCredit().equals(that.getIsCredit()) : that.getIsCredit() != null)
            return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (getCurrency() != null ? !getCurrency().equals(that.getCurrency()) : that.getCurrency() != null)
            return false;
        if (getPostedBalance() != null ? !getPostedBalance().equals(that.getPostedBalance()) : that.getPostedBalance() != null)
            return false;
        if (getPostedTimestampUtc() != null ? !getPostedTimestampUtc().equals(that.getPostedTimestampUtc()) : that.getPostedTimestampUtc() != null)
            return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null) return false;
        if (getTransactionCode() != null ? !getTransactionCode().equals(that.getTransactionCode()) : that.getTransactionCode() != null)
            return false;
        return getLastUpdated() != null ? getLastUpdated().equals(that.getLastUpdated()) : that.getLastUpdated() == null;

    }

    @Override
    public int hashCode() {
        int result = getMessageKey() != null ? getMessageKey().hashCode() : 0;
        result = 31 * result + (getOutboxPublishedDate() != null ? getOutboxPublishedDate().hashCode() : 0);
        result = 31 * result + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result = 31 * result + (getUpdatedDate() != null ? getUpdatedDate().hashCode() : 0);
        result = 31 * result + (getEventType() != null ? getEventType().hashCode() : 0);
        result = 31 * result + (getEventTypeKeyName() != null ? getEventTypeKeyName().hashCode() : 0);
        result = 31 * result + (getEventMetadataVersion() != null ? getEventMetadataVersion().hashCode() : 0);
        result = 31 * result + (getTransactionId() != null ? getTransactionId().hashCode() : 0);
        result = 31 * result + (getCorrelationId() != null ? getCorrelationId().hashCode() : 0);
        result = 31 * result + (getSubscriptionKey() != null ? getSubscriptionKey().hashCode() : 0);
        result = 31 * result + (getIsCredit() != null ? getIsCredit().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        result = 31 * result + (getPostedBalance() != null ? getPostedBalance().hashCode() : 0);
        result = 31 * result + (getPostedTimestampUtc() != null ? getPostedTimestampUtc().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getTransactionCode() != null ? getTransactionCode().hashCode() : 0);
        result = 31 * result + (getLastUpdated() != null ? getLastUpdated().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionRecordFormat{" +
                "messageKey='" + messageKey + '\'' +
                ", outboxPublishedDate='" + outboxPublishedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventTypeKeyName='" + eventTypeKeyName + '\'' +
                ", eventMetadataVersion='" + eventMetadataVersion + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", subscriptionKey='" + subscriptionKey + '\'' +
                ", isCredit='" + isCredit + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", postedBalance='" + postedBalance + '\'' +
                ", postedTimestampUtc='" + postedTimestampUtc + '\'' +
                ", state='" + state + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
