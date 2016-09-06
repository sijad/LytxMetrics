package com.lytx.common.newrelic;

public class InsightPerformanceStats {

	private long fileReadCount;
	private long inspectedCount;
	private long omittedCount;
	private long matchCount;
	private long processCount;
	private long rawBytes;
	private long decompressedBytes;
	private long responseBodyBytes;
	private long fileProcessingTime;
	private long slowLaneWaitTime;
	private float sumSubqueryWeight;
	private float sumFileProcessingTimePercentile;
	private long subqueryWeightUpdates;
	private long sumSubqueryWeightStartFileProcessingTime;
	private long runningQueriesTotal;
	private long ignoredFiles;
	private long mergeTime;
	private long ioTime;
	private long decompressionTime;
	private long wallClockTime;

	
	public long getFileReadCount() {
		return fileReadCount;
	}
	public void setFileReadCount(long fileReadCount) {
		this.fileReadCount = fileReadCount;
	}
	public long getInspectedCount() {
		return inspectedCount;
	}
	public void setInspectedCount(long inspectedCount) {
		this.inspectedCount = inspectedCount;
	}
	public long getOmittedCount() {
		return omittedCount;
	}
	public void setOmittedCount(long omittedCount) {
		this.omittedCount = omittedCount;
	}
	public long getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(long matchCount) {
		this.matchCount = matchCount;
	}
	public long getProcessCount() {
		return processCount;
	}
	public void setProcessCount(long processCount) {
		this.processCount = processCount;
	}
	public long getRawBytes() {
		return rawBytes;
	}
	public void setRawBytes(long rawBytes) {
		this.rawBytes = rawBytes;
	}
	public long getDecompressedBytes() {
		return decompressedBytes;
	}
	public void setDecompressedBytes(long decompressedBytes) {
		this.decompressedBytes = decompressedBytes;
	}
	public long getResponseBodyBytes() {
		return responseBodyBytes;
	}
	public void setResponseBodyBytes(long responseBodyBytes) {
		this.responseBodyBytes = responseBodyBytes;
	}
	public long getFileProcessingTime() {
		return fileProcessingTime;
	}
	public void setFileProcessingTime(long fileProcessingTime) {
		this.fileProcessingTime = fileProcessingTime;
	}
	public long getSlowLaneWaitTime() {
		return slowLaneWaitTime;
	}
	public void setSlowLaneWaitTime(long slowLaneWaitTime) {
		this.slowLaneWaitTime = slowLaneWaitTime;
	}
	public float getSumSubqueryWeight() {
		return sumSubqueryWeight;
	}
	public void setSumSubqueryWeight(float sumSubqueryWeight) {
		this.sumSubqueryWeight = sumSubqueryWeight;
	}
	public float getSumFileProcessingTimePercentile() {
		return sumFileProcessingTimePercentile;
	}
	public void setSumFileProcessingTimePercentile(float sumFileProcessingTimePercentile) {
		this.sumFileProcessingTimePercentile = sumFileProcessingTimePercentile;
	}
	public long getSubqueryWeightUpdates() {
		return subqueryWeightUpdates;
	}
	public void setSubqueryWeightUpdates(long subqueryWeightUpdates) {
		this.subqueryWeightUpdates = subqueryWeightUpdates;
	}
	public long getSumSubqueryWeightStartFileProcessingTime() {
		return sumSubqueryWeightStartFileProcessingTime;
	}
	public void setSumSubqueryWeightStartFileProcessingTime(long sumSubqueryWeightStartFileProcessingTime) {
		this.sumSubqueryWeightStartFileProcessingTime = sumSubqueryWeightStartFileProcessingTime;
	}
	public long getRunningQueriesTotal() {
		return runningQueriesTotal;
	}
	public void setRunningQueriesTotal(long runningQueriesTotal) {
		this.runningQueriesTotal = runningQueriesTotal;
	}
	public long getIgnoredFiles() {
		return ignoredFiles;
	}
	public void setIgnoredFiles(long ignoredFiles) {
		this.ignoredFiles = ignoredFiles;
	}
	public long getMergeTime() {
		return mergeTime;
	}
	public void setMergeTime(long mergeTime) {
		this.mergeTime = mergeTime;
	}
	public long getIoTime() {
		return ioTime;
	}
	public void setIoTime(long ioTime) {
		this.ioTime = ioTime;
	}
	public long getDecompressionTime() {
		return decompressionTime;
	}
	public void setDecompressionTime(long decompressionTime) {
		this.decompressionTime = decompressionTime;
	}
	public long getWallClockTime() {
		return wallClockTime;
	}
	public void setWallClockTime(long wallClockTime) {
		this.wallClockTime = wallClockTime;
	}
	
	
}
