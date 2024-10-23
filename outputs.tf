output "cluster_endpoint" {
  value = aws_eks_cluster.my_cluster.endpoint
}

output "cluster_name" {
  value = aws_eks_cluster.my_cluster.name
}

output "dynamodb_table_name" {
  value = aws_dynamodb_table.FranchiseTable.name
}
