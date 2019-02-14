package com.nablarch.example.client;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.nablarch.example.dto.ProjectResponseDto;
import com.nablarch.example.form.ProjectForm;

public class InsertClient {

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

        // 登録
        ProjectForm project = createInsertProject();
        System.out.println("insert status:" + postProject(project));
        System.out.println(getProjects());

    }

    /**
     * 登録用プロジェクト情報生成
     * @return 登録用プロジェクト情報
     */
    private static ProjectForm createInsertProject() {
        ProjectForm form = new ProjectForm();
        form.setProjectName("プロジェクト９９９");
        form.setProjectType("development");
        form.setProjectClass("ss");
        form.setProjectStartDate("20160101");
        form.setProjectEndDate("20161231");
        form.setClientId("10");
        form.setProjectManager("山田");
        form.setProjectLeader("田中");
        form.setNote("備考９９９");
        form.setSales("10000");
        form.setCostOfGoodsSold("20000");
        form.setSga("30000");
        form.setAllocationOfCorpExpenses("40000");
        return form;
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
     * HTTP POSTメソッドを使用したクライアント操作を行う。
     * @param project 更新用プロジェクト情報
     * @return ステータスコード
     */
    private static Integer postProject(ProjectForm project) {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(project)).getStatus();
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
