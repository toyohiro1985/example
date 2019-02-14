package com.nablarch.example.client;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.nablarch.example.dto.ProjectResponseDto;

public class SearchClient {

    private static final String targetUrl = "http://localhost:9080/projects";

    /**
     * プロジェクト情報の文字列変換を行う。
     * @param args 引数
     */
    public static void main(String[] args) throws Exception {

        // 検索
        // 全件検索
        System.out.print(getProjects());
        // 指定条件検索
        System.out.print(getProjects("clientId", 1));

     }

     /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @return プロジェクト情報リスト
     */
    private static List<Map<String, String>> getProjects() {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Map<String, String>>>() {});
    }

    /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @param key query paramのキー
     * @param value query paramの値
     * @return プロジェクト情報リスト
     */
    private static List<Map<String, String>> getProjects(String key, Object value) throws UnsupportedEncodingException {

        return ClientBuilder.newClient()
                            .target(targetUrl)
                            .queryParam(key, value)
                            .request(MediaType.APPLICATION_JSON)
                            .get(new GenericType<List<Map<String, String>>>() {});
    }

     /**
     * プロジェクト情報の文字列変換を行う。
     * @param projects プロジェクト情報List
     * @return プロジェクト情報
     */
    private static String makeDataString(List<ProjectResponseDto> projects) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("---- projects (size: %s) ----", projects.size())).append('\n');
        for (ProjectResponseDto project : projects) {
            sb.append(String.format("Project(ProjectId: %s, ProjectName: %s, ProjectType: %s, ProjectClass: %s, "
                    + "ProjectStartDate: %s, ProjectEndDate: %s, ClientId: %s, ProjectManager: %s, ProjectLeader: %s, "
                    + "UserId: %s, Note: %s, Sales: %s, CostOfGoodsSold: %s, Sga: %s, AllocationOfCorpExpenses: %s, "
                    + "Client: %s, SystemAccount: %s)",
                    project.getProjectId(), project.getProjectName(), project.getProjectType(),
                    project.getProjectClass(), project.getProjectStartDate(), project.getProjectEndDate(),
                    project.getClientId(), project.getProjectManager(), project.getProjectLeader(),
                    project.getUserId(), project.getNote(), project.getSales(), project.getCostOfGoodsSold(),
                    project.getSga(), project.getAllocationOfCorpExpenses(), project.getClient(),
                    project.getSystemAccount())).append('\n');
        }
        return sb.toString();
    }
}
